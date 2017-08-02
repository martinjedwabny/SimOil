package CriterioDeReinyeccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Simulador.Pozo;
import Simulador.StockDeTanques;
import Simulador.Tanque;
import Tareas.TareaReinyeccion;
import Unidades.PresionEnHectopascales;
import Unidades.VolumenEnMetrosCubicos;

public class CriterioDeReinyeccionLoMasQuePueda extends CriterioDeReinyeccion {

	private PresionEnHectopascales cotaPresion;
	
	public CriterioDeReinyeccionLoMasQuePueda(PresionEnHectopascales cotaPresion) {
		this.cotaPresion = cotaPresion;
	}

	public List<TareaReinyeccion> planearReinyeccion(
			VolumenEnMetrosCubicos volumenGlobalExtraido,
			VolumenEnMetrosCubicos volumenGlobalReinyectado,
			List<Pozo> pozos, StockDeTanques tanquesDeAgua) {
		
		ArrayList<TareaReinyeccion> reinyecciones = new ArrayList<TareaReinyeccion>();
		
		float maxAReinyectar = volumenGlobalExtraido.dameVolumen() - volumenGlobalReinyectado.dameVolumen();
		
		Map<Tanque, VolumenEnMetrosCubicos> aguaDisponiblePorTanque = new HashMap<Tanque, VolumenEnMetrosCubicos>();
		VolumenEnMetrosCubicos aguaDisponibleTotal = new VolumenEnMetrosCubicos(0f);
		for (Tanque t : tanquesDeAgua.dameTanques()) {
			VolumenEnMetrosCubicos volumenOcupado = t.dameVolumenOcupado();
			aguaDisponibleTotal.sumar(volumenOcupado);
			aguaDisponiblePorTanque.put(t, volumenOcupado);
		}
		
		maxAReinyectar = Math.min(maxAReinyectar, aguaDisponibleTotal.dameVolumen());
		
		ArrayList<Pozo> pozosAReinyectar = new ArrayList<Pozo>();
		
		for (Pozo pozo : pozos) {
			if (pozo.presionActual().menorOIgualQue(cotaPresion)) {
				pozosAReinyectar.add(pozo);
			}
		}
		
		if (pozosAReinyectar.size() == 0) {
			return reinyecciones;
		}
		
		// divido el volumen maximo a reinyectar por la cantidad de pozos que quiero reinyectar
		float volumenAReinyectarPorPozo = maxAReinyectar / pozosAReinyectar.size();
		
		for (Pozo pozo: pozosAReinyectar) {
			float necesario = volumenAReinyectarPorPozo;
			for (Tanque t : tanquesDeAgua.dameTanques()) {
				if (necesario > 0) {
					float aguaDisponibleEnTanque = aguaDisponiblePorTanque.get(t).dameVolumen();
					if (aguaDisponibleEnTanque > 0) {
						float volumenAUsar = Math.min(aguaDisponibleEnTanque, necesario);
						VolumenEnMetrosCubicos newVol = new VolumenEnMetrosCubicos(aguaDisponibleEnTanque-volumenAUsar);
						aguaDisponiblePorTanque.put(t, newVol);
						TareaReinyeccion tr = new TareaReinyeccion(pozo, t, new VolumenEnMetrosCubicos(volumenAUsar));
						reinyecciones.add(tr);
						necesario = necesario - volumenAUsar;
					}
				}
			}
		}
		return reinyecciones;
	}
}

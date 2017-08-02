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

public class CriterioDeReinyeccionCantidadDeLitrosFijosCuandoLaPresionEsMenor extends CriterioDeReinyeccion {

	private PresionEnHectopascales cotaPresion;
	private VolumenEnMetrosCubicos aguaAReinyectar;
	
	public CriterioDeReinyeccionCantidadDeLitrosFijosCuandoLaPresionEsMenor(PresionEnHectopascales cotaPresion, VolumenEnMetrosCubicos aguaAReinyectar) {
		this.cotaPresion = cotaPresion;
		this.aguaAReinyectar = aguaAReinyectar;
	}

	public List<TareaReinyeccion> planearReinyeccion(
			VolumenEnMetrosCubicos volumenGlobalExtraido,
			VolumenEnMetrosCubicos volumenGlobalReinyectado,
			List<Pozo> pozos, StockDeTanques tanquesDeAgua) {
		
		ArrayList<TareaReinyeccion> reinyecciones = new ArrayList<TareaReinyeccion>();
		
		float maxAReinyectar = volumenGlobalExtraido.dameVolumen() - volumenGlobalReinyectado.dameVolumen();
		if (aguaAReinyectar.dameVolumen() > maxAReinyectar ) {
			// todavía no extrajimos lo suficiente para poder reinyectar lo que nos pide este criterio
			return reinyecciones;
		}
		
		Map<Tanque, VolumenEnMetrosCubicos> aguaDisponiblePorTanque = new HashMap<Tanque, VolumenEnMetrosCubicos>();
		for (Tanque t : tanquesDeAgua.dameTanques()) {
			aguaDisponiblePorTanque.put(t, t.dameVolumenOcupado());
		}
		for (Pozo pozo : pozos) {
			if (pozo.presionActual().menorOIgualQue(cotaPresion)) {
				float necesario = aguaAReinyectar.dameVolumen();
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
		}
		return reinyecciones;
	}
}

package CriterioDeExtraccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Simulador.PlantaSeparadora;
import Simulador.Pozo;
import Simulador.Reservorio;
import Simulador.StockDePlantasSeparadoras;
import Tareas.TareaExtraccion;
import Unidades.PresionEnHectopascales;
import Unidades.VolumenEnMetrosCubicos;

public class CriterioDeExtraccionSacoLoMasQuePueda extends CriterioDeExtraccion {

	private float alpha1;
	private float alpha2;

	public CriterioDeExtraccionSacoLoMasQuePueda(float alpha1, float alpha2) {
		this.alpha1 = alpha1;
		this.alpha2 = alpha2;
	}

	@Override
	public List<TareaExtraccion> planearExtraccion(List<Pozo> listaPresionesDePozos, StockDePlantasSeparadoras plantasSeparadoras, Reservorio reservorio) {
		List<TareaExtraccion> extracciones = new ArrayList<>();
		
		Map<PlantaSeparadora, VolumenEnMetrosCubicos> volumenAProcesarEnCadaPlanta = new HashMap<>();
		List<PlantaSeparadora> plantas = plantasSeparadoras.damePlantasSeparadoras();
		
		// inicializo volumen a procesar en 0
		for (PlantaSeparadora planta: plantas) {
			volumenAProcesarEnCadaPlanta.put(planta, new VolumenEnMetrosCubicos(0f));
		}
 
		// espacio almacenable en tanques
		float compuestoProcesableSegunTanques = 0f;
		if (!plantas.isEmpty()) {
			compuestoProcesableSegunTanques = Math.min(
					plantas.get(0).compuestoProcesableSegunTanquesAgua(reservorio.dameCompuesto()).dameVolumen(),
					plantas.get(0).compuestoProcesableSegunTanquesGas(reservorio.dameCompuesto()).dameVolumen());
		}
		
		// para cada pozo, me fijo cuanto puedo extraer y a donde lo voy a mandar a procesar		
		for (Pozo pozo : listaPresionesDePozos) {
			float volumenAExtraerMaximo = volumenPotencialPorPozo(pozo, listaPresionesDePozos).dameVolumen();
			
			for (PlantaSeparadora planta: plantas) {
				// me fijo si hace falta extraer mas
				if (volumenAExtraerMaximo <= 0.0001)
					break;
				VolumenEnMetrosCubicos volumenMaximoAProcesar = planta.dameProcesamientoMaximoDiario();
				
				VolumenEnMetrosCubicos volumenAProcesarHastaAhora = volumenAProcesarEnCadaPlanta.get(planta);
				
				float volumenMaximoAProcesarEnPlanta = volumenMaximoAProcesar.dameVolumen() - volumenAProcesarHastaAhora.dameVolumen();
				
				float aExtraer = 
						Math.min(volumenMaximoAProcesarEnPlanta, 
								Math.min(compuestoProcesableSegunTanques,
										volumenAExtraerMaximo));
				
				compuestoProcesableSegunTanques -= aExtraer;
				volumenAExtraerMaximo -= aExtraer;
				
				if (aExtraer >= 0.0001) {
					VolumenEnMetrosCubicos volumenAExtraer = new VolumenEnMetrosCubicos(aExtraer);
					volumenAProcesarHastaAhora.sumar(volumenAExtraer);
					volumenAProcesarEnCadaPlanta.put(planta, volumenAProcesarHastaAhora);
					extracciones.add(new TareaExtraccion(pozo, volumenAExtraer, planta));
				}
			}
		}
		
		return extracciones;
	}
	
	public VolumenEnMetrosCubicos volumenPotencialPorPozo(Pozo pozo, List<Pozo> listaPresiones) {
		PresionEnHectopascales presionPozo = pozo.presionActual();
		float cantidadPozosHabilitados = listaPresiones.size();
		float vol = (float) (alpha1 * (presionPozo.damePresion() / cantidadPozosHabilitados) +
				alpha2 * Math.pow((double)(presionPozo.damePresion() / cantidadPozosHabilitados), 2.0));
		return new VolumenEnMetrosCubicos(vol);
	}
}

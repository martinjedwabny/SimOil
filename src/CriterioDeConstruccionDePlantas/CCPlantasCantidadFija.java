package CriterioDeConstruccionDePlantas;

import java.util.ArrayList;
import java.util.List;

import Constructoras.ConstructoraDePlantaSeparadora;
import Simulador.PlantaSeparadora;
import Simulador.Tanque;
import Tareas.PedidoPlanta;
import Unidades.VolumenEnMetrosCubicos;

public class CCPlantasCantidadFija extends CriterioDeConstruccionDePlantas {
	
	private int cantidad;
	private VolumenEnMetrosCubicos volumen;

	public CCPlantasCantidadFija(int cantidad, VolumenEnMetrosCubicos volumen) {
		this.cantidad = cantidad;
		this.volumen = volumen;
	}

	@Override
	public List<PedidoPlanta> planearPlantas(List<PlantaSeparadora> plantasActuales, List<Tanque> tanquesGas,
			List<Tanque> tanquesAgua, ConstructoraDePlantaSeparadora constructoraDePlantas) {
		List<PedidoPlanta> construcciones = new ArrayList<>();
		int plantasRestantes = cantidad - (plantasActuales.size() + constructoraDePlantas.cantidadObrasEnProgreso());  
		for(int i = 0; i < plantasRestantes; i++) {			
			construcciones.add(new PedidoPlanta(volumen, constructoraDePlantas));
		}
		return construcciones;
	}

}

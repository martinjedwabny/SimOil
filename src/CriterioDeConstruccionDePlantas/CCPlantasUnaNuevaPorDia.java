package CriterioDeConstruccionDePlantas;

import java.util.ArrayList;
import java.util.List;

import Constructoras.ConstructoraDePlantaSeparadora;
import Simulador.PlantaSeparadora;
import Simulador.Tanque;
import Tareas.PedidoPlanta;
import Unidades.VolumenEnMetrosCubicos;

public class CCPlantasUnaNuevaPorDia extends CriterioDeConstruccionDePlantas {
	
	private VolumenEnMetrosCubicos volumen;

	public CCPlantasUnaNuevaPorDia(VolumenEnMetrosCubicos volumen) {
		this.volumen = volumen;
	}

	@Override
	public List<PedidoPlanta> planearPlantas(List<PlantaSeparadora> plantasActuales, List<Tanque> tanquesGas,
			List<Tanque> tanquesAgua, ConstructoraDePlantaSeparadora constructoraDePlantas) {
		List<PedidoPlanta> construcciones = new ArrayList<>();
		construcciones.add(new PedidoPlanta(volumen, constructoraDePlantas));
		return construcciones;
	}

}

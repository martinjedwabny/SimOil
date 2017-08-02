package CriterioDeConstruccionDePlantas;

import java.util.List;

import Constructoras.ConstructoraDePlantaSeparadora;
import Simulador.PlantaSeparadora;
import Simulador.Tanque;
import Tareas.PedidoPlanta;

public abstract class CriterioDeConstruccionDePlantas {

	public abstract List<PedidoPlanta> planearPlantas(List<PlantaSeparadora> plantasActuales,
			List<Tanque> tanquesGas, List<Tanque> tanquesAgua, ConstructoraDePlantaSeparadora constructoraDePlantas);
}

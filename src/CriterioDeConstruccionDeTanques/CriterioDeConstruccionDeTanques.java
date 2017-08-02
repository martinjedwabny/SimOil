package CriterioDeConstruccionDeTanques;

import java.util.List;

import Constructoras.ConstructoraDeTanqueDeAgua;
import Constructoras.ConstructoraDeTanqueDeGas;
import Simulador.Tanque;
import Tareas.PedidoTanque;

public abstract class CriterioDeConstruccionDeTanques {

	public abstract List<PedidoTanque> planearTanques(List<Tanque> tanquesDeAgua, List<Tanque> tanquesDeGas,
			ConstructoraDeTanqueDeAgua constructoraDeTanqueDeAgua,
			ConstructoraDeTanqueDeGas constructoraDeTanqueDeGas);

}

package CriterioDeConstruccionDeTanques;

import java.util.ArrayList;
import java.util.List;

import Constructoras.ConstructoraDeTanqueDeAgua;
import Constructoras.ConstructoraDeTanqueDeGas;
import Simulador.Tanque;
import Tareas.PedidoTanque;
import Unidades.VolumenEnMetrosCubicos;

public class CCTanquesUnoNuevoPorDia extends CriterioDeConstruccionDeTanques {
	
	private VolumenEnMetrosCubicos volumen;

	public CCTanquesUnoNuevoPorDia(VolumenEnMetrosCubicos volumen) {
		this.volumen = volumen;
	}

	@Override
	public List<PedidoTanque> planearTanques(List<Tanque> tanquesDeAgua, List<Tanque> tanquesDeGas,
			ConstructoraDeTanqueDeAgua constructoraDeTanqueDeAgua,
			ConstructoraDeTanqueDeGas constructoraDeTanqueDeGas) {
		List<PedidoTanque> construcciones = new ArrayList<>();
		construcciones.add(new PedidoTanque(volumen, constructoraDeTanqueDeGas));
		construcciones.add(new PedidoTanque(volumen, constructoraDeTanqueDeAgua));
		return construcciones;
	}

}

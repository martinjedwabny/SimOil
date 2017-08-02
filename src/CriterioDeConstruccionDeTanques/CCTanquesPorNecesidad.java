package CriterioDeConstruccionDeTanques;

import java.util.ArrayList;
import java.util.List;

import Constructoras.ConstructoraDeTanqueDeAgua;
import Constructoras.ConstructoraDeTanqueDeGas;
import Simulador.Tanque;
import Tareas.PedidoTanque;
import Unidades.VolumenEnMetrosCubicos;

public class CCTanquesPorNecesidad extends CriterioDeConstruccionDeTanques {
	
	private VolumenEnMetrosCubicos volumen;
	private int cantidad;

	public CCTanquesPorNecesidad(int cantidad, VolumenEnMetrosCubicos volumen) {
		this.cantidad = cantidad;
		this.volumen = volumen;
	}

	@Override
	public List<PedidoTanque> planearTanques(List<Tanque> tanquesDeAgua, List<Tanque> tanquesDeGas,
			ConstructoraDeTanqueDeAgua constructoraDeTanqueDeAgua,
			ConstructoraDeTanqueDeGas constructoraDeTanqueDeGas) {
		List<PedidoTanque> construcciones = new ArrayList<>();
		
		if (constructoraDeTanqueDeAgua.cantidadObrasEnProgreso() == 0) {
			float volumenOcupado = 0f;
			float volumenMaximo = 0f;
			
			for (Tanque tanque: tanquesDeAgua) {
				volumenMaximo += tanque.dameVolumenMaximo().dameVolumen();
				volumenOcupado += tanque.dameVolumenOcupado().dameVolumen();
			}
			
			float volumenDisponible = volumenMaximo - volumenOcupado;
			
			if (volumenDisponible < 0.01f) {
				for(int i = 0; i < cantidad; i++) {			
					construcciones.add(new PedidoTanque(volumen, constructoraDeTanqueDeAgua));
				}
			}
		}
		
		if (constructoraDeTanqueDeGas.cantidadObrasEnProgreso() == 0) {
			float volumenOcupado = 0f;
			float volumenMaximo = 0f;
			
			for (Tanque tanque: tanquesDeGas) {
				volumenMaximo += tanque.dameVolumenMaximo().dameVolumen();
				volumenOcupado += tanque.dameVolumenOcupado().dameVolumen();
			}
			
			float volumenDisponible = volumenMaximo - volumenOcupado;
			
			if (volumenDisponible < 0.01f) {
				for(int i = 0; i < cantidad; i++) {			
					construcciones.add(new PedidoTanque(volumen, constructoraDeTanqueDeGas));
				}
			}
		}

		return construcciones;
	}

}

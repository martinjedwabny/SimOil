package CriterioDeConstruccionDeTanques;

import java.util.ArrayList;
import java.util.List;

import Constructoras.ConstructoraDeTanqueDeAgua;
import Constructoras.ConstructoraDeTanqueDeGas;
import Simulador.Tanque;
import Tareas.PedidoTanque;
import Unidades.VolumenEnMetrosCubicos;

public class CCTanquesCantidadFija extends CriterioDeConstruccionDeTanques {
	
	private VolumenEnMetrosCubicos volumen;
	private int cantidad;

	public CCTanquesCantidadFija(int cantidad, VolumenEnMetrosCubicos volumen) {
		this.cantidad = cantidad;
		this.volumen = volumen;
	}

	@Override
	public List<PedidoTanque> planearTanques(List<Tanque> tanquesDeAgua, List<Tanque> tanquesDeGas,
			ConstructoraDeTanqueDeAgua constructoraDeTanqueDeAgua,
			ConstructoraDeTanqueDeGas constructoraDeTanqueDeGas) {
		List<PedidoTanque> construcciones = new ArrayList<>();
		
		int tanquesAguaRestantes = cantidad - (tanquesDeAgua.size() + constructoraDeTanqueDeAgua.cantidadObrasEnProgreso());  
		for(int i = 0; i < tanquesAguaRestantes; i++) {			
			construcciones.add(new PedidoTanque(volumen, constructoraDeTanqueDeAgua));
		}
		
		int tanquesGasRestantes = cantidad - (tanquesDeGas.size() + constructoraDeTanqueDeGas.cantidadObrasEnProgreso());  
		for(int i = 0; i < tanquesGasRestantes; i++) {			
			construcciones.add(new PedidoTanque(volumen, constructoraDeTanqueDeGas));
		}

		return construcciones;
	}

}

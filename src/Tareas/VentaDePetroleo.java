package Tareas;

import Simulador.RegistradorTarea;
import Unidades.VolumenEnMetrosCubicos;

public class VentaDePetroleo extends Tarea {
	
	private VolumenEnMetrosCubicos petroleoVendido;

	public VolumenEnMetrosCubicos damePetroleoVendido() {
		return petroleoVendido;
	}

	public VentaDePetroleo(VolumenEnMetrosCubicos petroleoVendido) {
		this.petroleoVendido = petroleoVendido;
	}

	public float dameGanancias(float precioVentaPetroleo) {
		return petroleoVendido.dameVolumen() * precioVentaPetroleo;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarVentaDePetroleo(this);
	}
}

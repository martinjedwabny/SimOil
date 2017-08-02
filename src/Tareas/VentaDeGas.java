package Tareas;

import Simulador.RegistradorTarea;
import Simulador.Tanque;
import Unidades.VolumenEnMetrosCubicos;

public class VentaDeGas extends Tarea {
	
	private VolumenEnMetrosCubicos unVolumen;
	private Tanque tanque;
	
	public VentaDeGas(VolumenEnMetrosCubicos unVolumen, Tanque unTanque) {
		this.unVolumen = unVolumen;
		this.tanque = unTanque;
	}
	
	public VolumenEnMetrosCubicos volumenDeVenta() {
		return unVolumen;
	}
	
	public Tanque tanqueDeOrigen() {
		return tanque;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarVentaDeGas(this);
	}
}

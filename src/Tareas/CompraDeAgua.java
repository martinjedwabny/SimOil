package Tareas;

import Simulador.RegistradorTarea;
import Unidades.VolumenEnMetrosCubicos;

public class CompraDeAgua extends Tarea {
	
	private VolumenEnMetrosCubicos unVolumen;

	public CompraDeAgua(VolumenEnMetrosCubicos unVolumen) {
		this.unVolumen = unVolumen;
	}
	
	public VolumenEnMetrosCubicos volumenDeCompra() {
		return unVolumen;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarCompraDeAgua(this);
	}
}

package Tareas;

import Constructoras.ConstructoraDeTanque;
import Simulador.RegistradorTarea;
import Unidades.VolumenEnMetrosCubicos;

public class PedidoTanque extends Tarea {
	
	private VolumenEnMetrosCubicos unVolumen;
	private ConstructoraDeTanque unaConstructora;

	public PedidoTanque(VolumenEnMetrosCubicos unVolumen, ConstructoraDeTanque unaConstructora) {
		this.unVolumen = unVolumen;
		this.unaConstructora = unaConstructora;
	}

	public ConstructoraDeTanque constructora() {
		return unaConstructora;
	}

	public VolumenEnMetrosCubicos volumenAConstruir() {
		return unVolumen;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarPedidoTanque(this);
	}
}
 
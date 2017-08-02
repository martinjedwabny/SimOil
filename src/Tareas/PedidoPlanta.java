package Tareas;

import Constructoras.ConstructoraDePlantaSeparadora;
import Simulador.RegistradorTarea;
import Unidades.VolumenEnMetrosCubicos;

public class PedidoPlanta extends Tarea {
	
	private ConstructoraDePlantaSeparadora unaConstructora;
	private VolumenEnMetrosCubicos unVolumen;

	public PedidoPlanta(VolumenEnMetrosCubicos unVolumen, ConstructoraDePlantaSeparadora unaConstructora) {
		this.unVolumen = unVolumen;
		this.unaConstructora = unaConstructora;
	}

	public ConstructoraDePlantaSeparadora constructora() {
		return unaConstructora;
	}

	public VolumenEnMetrosCubicos volumenAConstruir() {
		return unVolumen;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarPedidoPlanta(this);
	}
}

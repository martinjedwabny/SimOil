package Tareas;

import Simulador.Pozo;
import Simulador.RegistradorTarea;
import Simulador.Tanque;
import Unidades.VolumenEnMetrosCubicos;

public class TareaReinyeccion extends Tarea {
	
	private Pozo unPozo;
	private Tanque tanque;
	private VolumenEnMetrosCubicos volumen;
	
	
	public TareaReinyeccion(Pozo unPozo, Tanque tanque, VolumenEnMetrosCubicos volumen) {
		this.unPozo = unPozo;
		this.tanque = tanque;
		this.volumen = volumen;
	}

	public Pozo pozoAReinyectar() {
		return unPozo;
	}

	public Tanque tanqueDeDondeSacarAgua() {
		return tanque;
	}

	public VolumenEnMetrosCubicos volumenAReinyectar() {
		return volumen;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarReinyeccion(this);
	}
}

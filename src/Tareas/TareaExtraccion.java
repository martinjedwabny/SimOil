package Tareas;

import Simulador.PlantaSeparadora;
import Simulador.Pozo;
import Simulador.RegistradorTarea;
import Unidades.VolumenEnMetrosCubicos;

public class TareaExtraccion extends Tarea {
	
	private Pozo pozo;
	private VolumenEnMetrosCubicos unVolumen;
	private PlantaSeparadora unaPlantaSeparadora;

	public TareaExtraccion(Pozo pozo, 
			VolumenEnMetrosCubicos unVolumen,
			PlantaSeparadora unaPlantaSeparadora) {
		this.pozo = pozo;
		this.unVolumen = unVolumen;
		this.unaPlantaSeparadora = unaPlantaSeparadora;
	}
	
	public Pozo pozoAExtraer() {
		return pozo;
	}
	
	public VolumenEnMetrosCubicos volumenAExtraer() {
		return unVolumen;
	}
	
	public PlantaSeparadora plantaSeparadoraQueRecibeElVolumen() {
		return unaPlantaSeparadora;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarExtraccion(this);
	}
}

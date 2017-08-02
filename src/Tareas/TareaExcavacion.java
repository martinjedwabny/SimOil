package Tareas;

import ProveedoraDeRigs.RIG;
import Simulador.Parcela;
import Simulador.RegistradorTarea;

public class TareaExcavacion extends Tarea {
	
	private Parcela parcela;
	private RIG rig;
	
	public TareaExcavacion(Parcela unaParcela, RIG unRIG) {
		this.parcela = unaParcela;
		this.rig = unRIG;
	}

	public RIG rigAUtilizar() {
		return rig;
	}
	
	public Parcela parcelaAExcavar() {
		return parcela;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarExcavacion(this);
	}
}
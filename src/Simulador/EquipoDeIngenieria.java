package Simulador;

import java.util.ArrayList;
import java.util.Collection;

import CriterioDeTerminacion.CriterioDeTerminacion;
import Interfaces.Avanzable;
import Subequipo.Subequipo;
import Tareas.Tarea;

public class EquipoDeIngenieria implements Avanzable{

	private Collection<Subequipo> subequipos;
	private CriterioDeTerminacion criterioDeTerminacion;
	private RegistradorTarea registrador;
	
	public EquipoDeIngenieria(Collection<Subequipo> unosSubequipos, RegistradorTarea unRegistradorTarea, CriterioDeTerminacion unCriterioDeTerminacion) {
		this.subequipos = unosSubequipos;
		this.registrador = unRegistradorTarea;
		this.criterioDeTerminacion = unCriterioDeTerminacion;
	}
	
	@Override
	public void avanzarDia() {
		// Se realizan las tareas del día de hoy
		Collection<Tarea> tareasDelDia = new ArrayList<Tarea>();
		for (Subequipo unPlanificador : subequipos) {
			tareasDelDia.addAll(unPlanificador.realizarTareasDelDia());
		}
		
		registrarTareas(tareasDelDia);
	}
	
	private void registrarTareas(Collection<Tarea> tareasDelDia) {
		for(Tarea tarea : tareasDelDia) { 
			tarea.registrarseCon(registrador);
		}	
	}

//	public GuardarCostoYGanancia dameCostosYGanancias() {
//		return encargadoDeOp.dameCostosYGanancias();
//	}
	
	public boolean explotaciónFinalizada(){
		return this.criterioDeTerminacion.termino();
	}

	public void registrarNuevoDia(int dia) {
		registrador.registrarNuevoDia(dia);
	}

	public void registrarFinalizacion() {
		registrador.registrarFinalizacion();
	}

	public void registrarEstadoGeneral() {
		registrador.registrarEstadoGeneral();
	}

}

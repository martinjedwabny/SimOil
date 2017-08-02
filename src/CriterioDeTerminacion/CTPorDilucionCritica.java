package CriterioDeTerminacion;

import Simulador.Reservorio;

public class CTPorDilucionCritica extends CriterioDeTerminacion{
	
	private float dilucionCritica;
	private Reservorio reservorioDeSimulacion;
	
	public CTPorDilucionCritica(float unaDilusionCritica, Reservorio unReservorioDeSimulacion){
		this.dilucionCritica = unaDilusionCritica;
		this.reservorioDeSimulacion = unReservorioDeSimulacion;
	}

	@Override
	public Boolean termino() {
		float dilucionActual = reservorioDeSimulacion.dameCompuesto().damePorcentajeDePetroleoSobre100();
		System.out.println("La dilución actual es "+ dilucionActual);
		return dilucionActual < dilucionCritica;
	}
}

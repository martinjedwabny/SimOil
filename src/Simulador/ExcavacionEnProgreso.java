package Simulador;

import ProveedoraDeRigs.RIG;

public class ExcavacionEnProgreso {
	/*Al comienzo igual a lo que tiene una parcela*/
	private float distanciaRestanteAlReservorio;
	private Parcela parcela;
	private float precioCompraCombustible;
	
	public ExcavacionEnProgreso(Parcela parcela, float precioCompraCombustible){
		this.parcela = parcela;
		this.distanciaRestanteAlReservorio = parcela.distanciaInicialAlReservorio();
		this.precioCompraCombustible = precioCompraCombustible;
	}
	
	public void trabajarCon(RIG unRIG){
		float porcentajeResistenciaAlRIGSobre100 = parcela.getResistenciaAlRIG();
		float factorDeDureza = 1 - (porcentajeResistenciaAlRIGSobre100/100);
		float metrosExcavados = unRIG.damePotenciaDeExcavacionEnMetrosPorDia() * factorDeDureza;
		this.distanciaRestanteAlReservorio = Math.max(0, this.distanciaRestanteAlReservorio - metrosExcavados);
		float costoCombustible = unRIG.dameConsumoDiarioEnLitros() * precioCompraCombustible;
		System.out.println(
				String.format("Excavación en Parcela: %d usando RIG: %d. El costo de combustible fue: %.2f, faltan excavar %.2f metros (de %d m.)",
				parcela.hashCode(),
				unRIG.hashCode(),
				costoCombustible,
				this.distanciaRestanteAlReservorio,
				this.parcela.distanciaInicialAlReservorio()));
	}
	
	public boolean termino() {
		return this.distanciaRestanteAlReservorio==0;
	}
	
	public float distanciaRestante(){
		return this.distanciaRestanteAlReservorio;
	}
	
	public Parcela parcelaExcavando() {
		return parcela;
	}
	
}

package Simulador;

import Unidades.PresionEnHectopascales;
import Unidades.VolumenEnMetrosCubicos;

public class Pozo {
	private Reservorio reservorio;
	private Parcela parcela;
	private PresionEnHectopascales presionActual;
	
	public Pozo(Parcela parcela, Reservorio reservorio){
		this.parcela = parcela;
		this.reservorio = reservorio;
		this.presionActual = parcela.getPresionInicial();
	}
	
	public void extraer(VolumenEnMetrosCubicos unVolumen, PlantaSeparadora plantaSeparadora) {
		plantaSeparadora.separar(unVolumen, reservorio.dameCompuesto());
	}

	public PresionEnHectopascales presionInicial() {
		return parcela.getPresionInicial();
	}

	public PresionEnHectopascales presionActual() {
		return presionActual;
	}
	
	public void actualizarPresion(PresionEnHectopascales nuevaPresion) {
		presionActual = new PresionEnHectopascales(nuevaPresion);
	}
}

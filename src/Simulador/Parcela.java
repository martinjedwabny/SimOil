package Simulador;

import Unidades.PresionEnHectopascales;

public class Parcela {
	final private PresionEnHectopascales presionInicial;
	final private int distanciaEnMetrosAlReservorio;
	final private float resistenciaAlRIG;
	
	/**
	 * 
	 * @param unaPresionInicial
	 * @param unaDistanciaEnMetros
	 * @param unPorcentajeDeDureza
	 */
	public Parcela(PresionEnHectopascales unaPresionInicial, int unaDistanciaEnMetros, 
			float unPorcentajeDeDureza) {
		this.presionInicial = unaPresionInicial;
		this.distanciaEnMetrosAlReservorio = unaDistanciaEnMetros;
		this.resistenciaAlRIG = unPorcentajeDeDureza;
	}
	
	public PresionEnHectopascales getPresionInicial() {
		return presionInicial;
	}

	public float getResistenciaAlRIG() {
		return resistenciaAlRIG;
	}

	public int distanciaInicialAlReservorio() {
		return distanciaEnMetrosAlReservorio;
	}
	
}

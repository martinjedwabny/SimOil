package Simulador;

import Unidades.VolumenEnMetrosCubicos;

public class Tanque {
	private final VolumenEnMetrosCubicos volumenMaximo;
	private VolumenEnMetrosCubicos volumenOcupado;
	
	public Tanque(VolumenEnMetrosCubicos unVolumenMaximo){
		this.volumenMaximo = new VolumenEnMetrosCubicos(unVolumenMaximo);
		this.volumenOcupado = new VolumenEnMetrosCubicos(0);
		/*
		 * Esta es la alternativa a no usar un constructor vacio o con parámetro 0.
		 * Pero es estático, no me copa.
		 */
		//this.volumenOcupado = VolumenEnMetrosCubicos.volumenInicial();
	}
	
	/**
	 * Constructor por copia
	 * @param unTanque
	 */
	public Tanque(Tanque unTanque) {
		this.volumenMaximo = new VolumenEnMetrosCubicos(unTanque.dameVolumenMaximo());
		this.volumenOcupado = new VolumenEnMetrosCubicos(unTanque.dameVolumenOcupado());
	}

	public void almacenar(VolumenEnMetrosCubicos unVolumen){
		volumenOcupado.sumar(unVolumen);
	}
	public void extraer(VolumenEnMetrosCubicos unVolumen){
		volumenOcupado.restar(unVolumen);
	}

	public VolumenEnMetrosCubicos dameVolumenMaximo() {
		return volumenMaximo;
	}

	public VolumenEnMetrosCubicos dameVolumenOcupado() {
		return volumenOcupado;
	}
}

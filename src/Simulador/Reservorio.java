package Simulador;

import Unidades.CompuestoGasAguaPetroleo;
import Unidades.VolumenEnMetrosCubicos;

public class Reservorio {
	
	private CompuestoGasAguaPetroleo compuesto;
	private VolumenEnMetrosCubicos volumenInicial;
	private VolumenEnMetrosCubicos volumenActual;
	
	public Reservorio(CompuestoGasAguaPetroleo compuesto,
			VolumenEnMetrosCubicos volumenInicial,
			VolumenEnMetrosCubicos volumenActual){
		this.compuesto = compuesto;
		this.volumenActual = volumenActual;
		this.volumenInicial = volumenInicial;
	}
	
	public CompuestoGasAguaPetroleo dameCompuesto(){
		return new CompuestoGasAguaPetroleo(this.compuesto);
	}

	public VolumenEnMetrosCubicos dameVolumenActual() {
		return new VolumenEnMetrosCubicos(volumenActual);
	}
	
	public void setVolumenActual(VolumenEnMetrosCubicos vol) {
		this.volumenActual = vol;
	}

	public VolumenEnMetrosCubicos dameVolumenInicial() {
		return new VolumenEnMetrosCubicos(volumenInicial);
	}

	public void actualizarCompuesto(CompuestoGasAguaPetroleo nuevoCompuesto) {
		this.compuesto = new CompuestoGasAguaPetroleo(nuevoCompuesto);
	}
}

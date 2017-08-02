package Simulador;

import Unidades.VolumenEnMetrosCubicos;

public class Destileria {
	
	public Destileria() {
		volumenDePetroleoADestilarHoy = new VolumenEnMetrosCubicos(0f);
	}
	
	VolumenEnMetrosCubicos volumenDePetroleoADestilarHoy;
	
	public void destilar(VolumenEnMetrosCubicos unVolumenDePetroleo) {
		volumenDePetroleoADestilarHoy.sumar(unVolumenDePetroleo);
	}
	
	public VolumenEnMetrosCubicos volumenDestiladoHoy() {
		return volumenDePetroleoADestilarHoy;
	}

	public void vaciar() {
		volumenDePetroleoADestilarHoy = new VolumenEnMetrosCubicos(0f);
	}

}

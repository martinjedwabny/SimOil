package Unidades;

/*
 * Nos interesa que un VolumenEnMetrosCubicos no sea negativo, por eso existe esto.
 */

public class VolumenEnMetrosCubicos {
	static final float volumenInicial = 0f;
	float volumen;
	
	/*No me gusta que hayan varios constructores pero no se modelar bien la clase Volumen*/
	public VolumenEnMetrosCubicos(VolumenEnMetrosCubicos unVolumen) {
		this.volumen = unVolumen.dameVolumen();
	}
	
	public VolumenEnMetrosCubicos(float unVolumenEnMetrosCubicos) {
		this.volumen = Math.max(volumenInicial, unVolumenEnMetrosCubicos);
	}
	
	/*Operaciones sobre Volumenes*/
	public void sumar(VolumenEnMetrosCubicos unVolumen) {
		this.volumen += unVolumen.dameVolumen();
	}
	
	public void restar(VolumenEnMetrosCubicos unVolumen) {
		this.volumen = Math.max(0f, this.volumen - unVolumen.dameVolumen() );
	}

	public VolumenEnMetrosCubicos tomarUnPorcentajeSobre100(float porcentaje) {		
		VolumenEnMetrosCubicos unParteDelVolumen = new VolumenEnMetrosCubicos(this.volumen /100f * porcentaje);
		return unParteDelVolumen;
	}

	public float dameVolumen() {
		return this.volumen;
	}

	public boolean menorQue(VolumenEnMetrosCubicos otroVolumen) {
		return volumen < otroVolumen.volumen;
	}

	public static VolumenEnMetrosCubicos min(VolumenEnMetrosCubicos uno, VolumenEnMetrosCubicos otro) {
		if (uno.volumen < otro.volumen) {
			return uno;
		} else {
			return otro;
		}
	}
	
	public VolumenEnMetrosCubicos copy() {
		return new VolumenEnMetrosCubicos(volumen);
	}
}

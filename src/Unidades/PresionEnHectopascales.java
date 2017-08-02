package Unidades;

public class PresionEnHectopascales {
	
	double presionEnHectopascales;
	
	public PresionEnHectopascales(double d) {
		this.presionEnHectopascales = d;
	}
	
	public PresionEnHectopascales(PresionEnHectopascales otraPresion) {
		this.presionEnHectopascales = otraPresion.presionEnHectopascales;
	}

	public PresionEnHectopascales multiplicar(double exp) {
		PresionEnHectopascales nuevaPresion = new PresionEnHectopascales(this.presionEnHectopascales * exp);
		return nuevaPresion;
	}
	
	public boolean menorOIgualQue(PresionEnHectopascales otraPresion) {
		return presionEnHectopascales <= otraPresion.presionEnHectopascales;
	}

	public float damePresion() {
		return (float) presionEnHectopascales;
	}
}

package Unidades;

public class CompuestoGasAguaPetroleo {
	private float porcentajeDeGasSobre100;
	private float porcentajeDeAguaSobre100;
	private float porcentajeDePetroleoSobre100;
	
	
	public CompuestoGasAguaPetroleo(float unPorcentajeGasSobre100, float unPorcentajeAguaSobre100, 
			float unPorcentajePetroleoSobre100) {
		this.porcentajeDeGasSobre100 = unPorcentajeGasSobre100;
		this.porcentajeDeAguaSobre100 = unPorcentajeAguaSobre100;
		this.porcentajeDePetroleoSobre100 = unPorcentajePetroleoSobre100;
//		Chequeo para ver si los porcentajes suman 100
//		if (this.porcentajeDeAguaSobre100 + this.porcentajeDeGasSobre100 + this.porcentajeDePetroleoSobre100 != 100f) {
//			throw new IllegalArgumentException("Los porcentajes del compuesto no suman 100");
//		}
	}
	
	public CompuestoGasAguaPetroleo(CompuestoGasAguaPetroleo otroCompuesto){
		this.porcentajeDeGasSobre100 = otroCompuesto.porcentajeDeGasSobre100;
		this.porcentajeDeAguaSobre100 = otroCompuesto.porcentajeDeAguaSobre100;
		this.porcentajeDePetroleoSobre100 = otroCompuesto.porcentajeDePetroleoSobre100;
	}
	
	public float damePorcentajeDeGasSobre100() {
		return porcentajeDeGasSobre100;
	}

	public float damePorcentajeDeAguaSobre100() {
		return porcentajeDeAguaSobre100;
	}

	public float damePorcentajeDePetroleoSobre100() {
		return porcentajeDePetroleoSobre100;
	}
	
}

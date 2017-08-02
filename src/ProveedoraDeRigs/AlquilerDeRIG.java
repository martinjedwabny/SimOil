package ProveedoraDeRigs;
import Interfaces.Avanzable;

public class AlquilerDeRIG implements Avanzable{
	private float unMontoEnPesos;
	private int diasRestantes;
	private RIG RIGEnAlquiler;
	
	public AlquilerDeRIG(RIG unRIG, int unaCantidadDeDias, float unMontoEnPesos){
		this.unMontoEnPesos = unMontoEnPesos;
		this.diasRestantes = unaCantidadDeDias;
		this.RIGEnAlquiler = unRIG;
	}

	@Override
	public void avanzarDia() {
		this.diasRestantes = Math.max(0, diasRestantes-1);
	}
	
	public int dameDiasRestantes(){
		return this.diasRestantes;
	}
	
	public float dameMonto(){
		return this.unMontoEnPesos;
	}
	
	public RIG dameRIG(){
		return this.RIGEnAlquiler;
	}
	
	public boolean terminoAlquiler(){
		return this.diasRestantes==0;
	}
	
}

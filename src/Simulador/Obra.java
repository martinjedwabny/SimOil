package Simulador;
import Interfaces.Avanzable;

public class Obra implements Avanzable{
	
	private int cantidadDeDiasRestantes;
	private Closure closure;
	private String titulo;
	
	public Obra(int unaCantidadDeDiasRestantes,Closure unClosure){
		this.cantidadDeDiasRestantes = unaCantidadDeDiasRestantes;
		this.closure = unClosure;
	}
	
	public void cambiarTitulo(String nuevoTitulo) {
		titulo = nuevoTitulo;
	}
	
	public String dameTitulo() {
		return titulo;
	}

	@Override
	public void avanzarDia() {
		cantidadDeDiasRestantes-=1;
		if( cantidadDeDiasRestantes == 0 ){
			closure.run();
		}
	}

	public boolean terminasteDeConstruirte() {
		return cantidadDeDiasRestantes == 0;
	}
	
}

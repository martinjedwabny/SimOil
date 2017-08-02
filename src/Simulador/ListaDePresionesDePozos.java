package Simulador;

import java.util.Collection;
import java.util.HashMap;

import Unidades.PresionEnHectopascales;

public class ListaDePresionesDePozos {
	private HashMap<Pozo, PresionEnHectopascales> listaDePresiones;
	//Caso volumenReinyectado+VolumenReservodioDiaXXX == VolumenReservorioInicial
	private HashMap<Pozo, PresionEnHectopascales> listaDePresionesIniciales;
	
	public ListaDePresionesDePozos(){
		listaDePresiones = new HashMap<Pozo, PresionEnHectopascales>();
		listaDePresionesIniciales = new HashMap<Pozo, PresionEnHectopascales>();
	}
	
	public PresionEnHectopascales presionDe(Pozo unPozo){
		return listaDePresiones.get(unPozo);
	}
	
	public Collection<Pozo> damePozos() {
		return listaDePresiones.keySet();
	}
	
	/**
	 * Esto es agregar una parcela recien terminada de excavar
	 * @param unPozo
	 * @param unaPresion
	 */
	public void agregarUnaPresionDeUnPozo(Pozo unPozo, PresionEnHectopascales unaPresion){
		listaDePresiones.put(unPozo, unaPresion);
		listaDePresionesIniciales.put(unPozo, unaPresion);
		
	}
	
	public void actualizarPresionDelPozo(Pozo unPozo, PresionEnHectopascales nuevaPresion){
		listaDePresiones.put(unPozo, nuevaPresion);
	}
	
	/**
	 * En el caso en que se reinyecte y se llege al mismo volumen del reservorio que al inicio se 
	 * restablece esto para todos los pozos.
	 */
	public void restablecerPresionesIniciales(){
		for (Pozo unPozo : this.listaDePresionesIniciales.keySet()) {
			this.listaDePresiones.put(unPozo, this.listaDePresionesIniciales.get(unPozo) );
		}
	}
	
	/**
	 * Por copia
	 * @param unPozo
	 * @return
	 */
	public PresionEnHectopascales presionInicialDe(Pozo unPozo) {
		return new PresionEnHectopascales( this.listaDePresionesIniciales.get(unPozo) );
	}
}

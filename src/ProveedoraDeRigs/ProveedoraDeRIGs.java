package ProveedoraDeRigs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Interfaces.Avanzable;

/*
 * Justificación:
 * 		Queremos objetos inmutables y a fines de la simulación no tiene sentido agregar o cambiar items.
 * 		Map en vez de Dictionary porque está deprecado (según https://docs.oracle.com/javase/8/docs/api/java/util/Dictionary.html).
 */

public class ProveedoraDeRIGs implements Avanzable {

	private Map <RIG, Float> preciosEnPesosDeAlquilerDeRIGs;
	private Map <RIG, Integer> minimosDiasDeAlquilerDeRIGs;
	private List<AlquilerDeRIG> alquileres;
	private float precioCompraCombustible;
	
	
	/**
	 * @param unaListaDePreciosDeAlquilerDeRIGs
	 * @param unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs
	 * @throws Exception 
	 */
	public ProveedoraDeRIGs(Map<RIG, Float> unaListaDePreciosDeAlquilerDeRIGs,
			Map<RIG,Integer> unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs,
			float precioCompraCombustible) throws Exception {
		if( unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs.keySet() != unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs.keySet() )
			throw new Exception("Diferentes RIGs en listaDePrecios y minimaCantidadDeDías");
		
		this.preciosEnPesosDeAlquilerDeRIGs = new HashMap<RIG,Float>(unaListaDePreciosDeAlquilerDeRIGs);
		this.minimosDiasDeAlquilerDeRIGs = new HashMap<RIG,Integer>(unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs);
		this.alquileres = new ArrayList<AlquilerDeRIG>();
		this.precioCompraCombustible = precioCompraCombustible;
	}
	
	/**
	 * Revisar consistencia de esto
	 * @param unRIG
	 * @param unaCantidadDeDias
	 * @return AlquilerDeRIG por referencia
	 */
	public AlquilerDeRIG alquilar(RIG unRIG, int unaCantidadDeDias){
		float montoEnPesosPorAlquiler = preciosEnPesosDeAlquilerDeRIGs.get(unRIG) * minimosDiasDeAlquilerDeRIGs.get(unRIG);
		AlquilerDeRIG nuevoAlquiler = new AlquilerDeRIG(unRIG, unaCantidadDeDias, montoEnPesosPorAlquiler);
		alquileres.add(nuevoAlquiler);
		return nuevoAlquiler;
	}
	
	public int cantidadMinimaDeDíasParaAlquilar(RIG unRIG){
		return minimosDiasDeAlquilerDeRIGs.get(unRIG);
	}
	
	public float precioAlquierDiarioDe(RIG unRIG){
		return preciosEnPesosDeAlquilerDeRIGs.get(unRIG);
	}
	
	public List<RIG> RIGsDisponibles(){
		List<RIG> RIGsDisponibles = new ArrayList<RIG>(this.preciosEnPesosDeAlquilerDeRIGs.keySet());
		
		for (AlquilerDeRIG unAlquilerDeRIG : alquileres) {
			RIGsDisponibles.remove(unAlquilerDeRIG.dameRIG());
		}
		
		return RIGsDisponibles;
	}
	
	public List<AlquilerDeRIG> dameAlquileres(){
		return new ArrayList<AlquilerDeRIG>(alquileres);
	}
	
	public List<RIG> dameRIGsAlquilados(){
		List<RIG> RIGsAlquilados = new ArrayList<RIG>();
		for (AlquilerDeRIG alquiler : this.dameAlquileres()) {
			RIGsAlquilados.add(alquiler.dameRIG());
		}
		return RIGsAlquilados;
	}
	
	/*Avanzable*/
	@Override
	public void avanzarDia() {
		for (Iterator iterator = alquileres.iterator(); iterator.hasNext();) {
			AlquilerDeRIG alquilerDeRIG = (AlquilerDeRIG) iterator.next();
			alquilerDeRIG.avanzarDia();
			
			if(alquilerDeRIG.terminoAlquiler()) {				
				// aca no hace falta cobrar, porque ya lo hace el RegistradorTarea cuando se arma el PedidoRIG
				iterator.remove();
			}
			
		}
	}

	public float damePrecioCompraCombustible() {
		return precioCompraCombustible;
	}
	
}
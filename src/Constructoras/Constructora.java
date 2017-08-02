package Constructoras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import FormaDeCalcularElCosto.IFormaDeCalcularElCosto;
import FormasDeEstimarTiempo.IFormaDeEstimarElTiempo;
import Interfaces.Avanzable;
import Simulador.Obra;
import Unidades.VolumenEnMetrosCubicos;

public abstract class Constructora implements Avanzable {
	
	private Collection<Obra> obras;
	private IFormaDeEstimarElTiempo formaDeEstimarElTiempo;
	private IFormaDeCalcularElCosto formaDeCalcularElCosto;
	
	public Constructora(IFormaDeCalcularElCosto unaFormaDeCalcularCosto, 
			IFormaDeEstimarElTiempo unaFormaDeEstimarElTiempo) {
		this.formaDeCalcularElCosto = unaFormaDeCalcularCosto;
		this.formaDeEstimarElTiempo = unaFormaDeEstimarElTiempo;
		obras = new ArrayList<Obra>();
	}
	
	protected void agregarObra(Obra unaObra){
		obras.add(unaObra);
	}
	
	/*Aunque no va a cambiar en runtime, por simplicidad se decidio por Strategy para calcular estas dos cosas.*/
	public int estimarTiempoEnDiasPara(VolumenEnMetrosCubicos unVolumenDeObra) {
		return formaDeEstimarElTiempo.estimarTiempoEnDias(unVolumenDeObra);
	}
	
	public float cotizarParaUn(VolumenEnMetrosCubicos unVolumenDeObra){
		return formaDeCalcularElCosto.calcularCosto(unVolumenDeObra);
	}
	
	/**
	 * Va a dependender de si hago tanques de agua/gas o plantas separadoras.
	 * Considerar quitar la esaObra de obras al finalizar.
	 * */
	public abstract void empezarObra(VolumenEnMetrosCubicos unVolumenDeObra);
	
	/*Interfaz Avanzable*/
	public void avanzarDia(){
		Iterator<Obra> it = obras.iterator();
		while (it.hasNext()) {
			Obra obra = it.next();
			obra.avanzarDia();
			if(obra.terminasteDeConstruirte()) {
				System.out.println(String.format("Termino obra de: %s", obra.dameTitulo()));
				it.remove();
			}
		}
	}
	
	public int cantidadObrasEnProgreso() {
		return obras.size();
	}
	
}

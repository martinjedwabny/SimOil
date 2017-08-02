package Simulador;

import java.util.ArrayList;

import Constructoras.ConstructoraDePlantaSeparadora;
import Constructoras.ConstructoraDeTanqueDeAgua;
import Constructoras.ConstructoraDeTanqueDeGas;
import Interfaces.Avanzable;
import ProveedoraDeRigs.ProveedoraDeRIGs;

public class AvanzadorDeDias {
	
	private EquipoDeIngenieria equipoDeIngenieria;
	private ConstructoraDeTanqueDeGas constructoraTanqueGas;
	private ConstructoraDeTanqueDeAgua constructoraTanqueAgua;
	private ConstructoraDePlantaSeparadora constructoraPlantas;
	private ArrayList<Avanzable> planesParaElDia;
	private ProveedoraDeRIGs proveedorRIGs;

	public AvanzadorDeDias(EquipoDeIngenieria equipoDeIngenieria,
			ConstructoraDeTanqueDeGas constructoraTanqueGas,
			ConstructoraDeTanqueDeAgua constructoraTanqueAgua,
			ConstructoraDePlantaSeparadora constructoraPlantas,
			ProveedoraDeRIGs proveedorRIGs) {
		this.equipoDeIngenieria = equipoDeIngenieria;
		this.constructoraTanqueGas = constructoraTanqueGas;
		this.constructoraTanqueAgua = constructoraTanqueAgua;
		this.constructoraPlantas = constructoraPlantas;
		this.proveedorRIGs = proveedorRIGs;
		
		inicializarPlanesParaElDia();
	}
	
	private void inicializarPlanesParaElDia() {
		this.planesParaElDia= new ArrayList<>();
		this.planesParaElDia.add(constructoraTanqueAgua);
		this.planesParaElDia.add(constructoraTanqueGas);
		this.planesParaElDia.add(constructoraPlantas);
		this.planesParaElDia.add(proveedorRIGs);
		this.planesParaElDia.add(equipoDeIngenieria);
	}

	public void avanzarDiasHastaTerminacion(Thread simluationThread) {
		int dia = 0;
		while (!equipoDeIngenieria.explotaciónFinalizada()) {
			if (simluationThread != null && simluationThread.isInterrupted()) {
				break;
			}
			dia++;
			equipoDeIngenieria.registrarNuevoDia(dia);
			for(Avanzable plan : planesParaElDia) {
				plan.avanzarDia();
			}
			equipoDeIngenieria.registrarEstadoGeneral();
		}
		equipoDeIngenieria.registrarFinalizacion();
	}
}

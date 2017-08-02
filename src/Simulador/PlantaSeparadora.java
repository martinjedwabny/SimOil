package Simulador;

import Unidades.CompuestoGasAguaPetroleo;
import Unidades.VolumenEnMetrosCubicos;

public class PlantaSeparadora {
	
	private StockDeTanques tanquesDeAgua;
	private StockDeTanques tanquesDeGas;
	private final Destileria destileria;
	private final VolumenEnMetrosCubicos procesamientoMaximoDiario;
	
	public PlantaSeparadora(StockDeTanques tanquesDeAgua, StockDeTanques tanquesDeGas, Destileria unaDestileria, 
			VolumenEnMetrosCubicos unVolDeProcesamientoMaximoDiario){
		this.tanquesDeAgua = tanquesDeAgua;
		this.tanquesDeGas = tanquesDeGas;
		this.destileria = unaDestileria;
		this.procesamientoMaximoDiario = new VolumenEnMetrosCubicos(unVolDeProcesamientoMaximoDiario);
	}
	
	public VolumenEnMetrosCubicos dameProcesamientoMaximoDiario(){
		return this.procesamientoMaximoDiario;
	}
	
	public VolumenEnMetrosCubicos compuestoProcesableSegunTanquesAgua(CompuestoGasAguaPetroleo comp) {
		float espacioLibreTanqueAgua = 0;
		for (Tanque t : this.tanquesDeAgua.dameTanques()) {
			espacioLibreTanqueAgua += t.dameVolumenMaximo().dameVolumen() - 
				t.dameVolumenOcupado().dameVolumen();
		}
		return new VolumenEnMetrosCubicos(espacioLibreTanqueAgua*(100f / comp.damePorcentajeDeAguaSobre100()));
	}
	
	public VolumenEnMetrosCubicos compuestoProcesableSegunTanquesGas(CompuestoGasAguaPetroleo comp) {
		float espacioLibreTanqueGas = 0;
		for (Tanque t : this.tanquesDeGas.dameTanques()) {
			espacioLibreTanqueGas += t.dameVolumenMaximo().dameVolumen() - 
				t.dameVolumenOcupado().dameVolumen();
		}
		return new VolumenEnMetrosCubicos(espacioLibreTanqueGas*(100f / comp.damePorcentajeDeGasSobre100()));
	}

	public void separar(VolumenEnMetrosCubicos unVolumenExtraido, CompuestoGasAguaPetroleo unCompuesto) {
		VolumenEnMetrosCubicos volumenPetroleo = separarPetroleo( unVolumenExtraido, unCompuesto);
		VolumenEnMetrosCubicos volumenAgua = separarAgua( unVolumenExtraido, unCompuesto);
		VolumenEnMetrosCubicos volumenGas = separarGas( unVolumenExtraido, unCompuesto);
		
		for (Tanque t : this.tanquesDeAgua.dameTanques()) {
			if (volumenAgua.dameVolumen() == 0)
				break;
			float almacenar = Math.min(t.dameVolumenMaximo().dameVolumen()-t.dameVolumenOcupado().dameVolumen(), volumenAgua.dameVolumen());
			t.almacenar(new VolumenEnMetrosCubicos(almacenar));
			volumenAgua.restar(new VolumenEnMetrosCubicos(almacenar));
		}
		
		for (Tanque t : this.tanquesDeGas.dameTanques()) {
			if (volumenGas.dameVolumen() == 0)
				break;
			float almacenar = Math.min(t.dameVolumenMaximo().dameVolumen()-t.dameVolumenOcupado().dameVolumen(), volumenGas.dameVolumen());
			t.almacenar(new VolumenEnMetrosCubicos(almacenar));
			volumenGas.restar(new VolumenEnMetrosCubicos(almacenar));
		}
		
		destileria.destilar(volumenPetroleo);		
	}

	private VolumenEnMetrosCubicos separarPetroleo(VolumenEnMetrosCubicos unVolumen, CompuestoGasAguaPetroleo unCompuesto) {
		return unVolumen.tomarUnPorcentajeSobre100(unCompuesto.damePorcentajeDePetroleoSobre100());
	}
	private VolumenEnMetrosCubicos separarGas(VolumenEnMetrosCubicos unVolumen, CompuestoGasAguaPetroleo unCompuesto) {
		return unVolumen.tomarUnPorcentajeSobre100(unCompuesto.damePorcentajeDeGasSobre100());
	}

	private VolumenEnMetrosCubicos separarAgua(VolumenEnMetrosCubicos unVolumen, CompuestoGasAguaPetroleo unCompuesto) {
		return unVolumen.tomarUnPorcentajeSobre100(unCompuesto.damePorcentajeDeAguaSobre100());
	}
	
	
}

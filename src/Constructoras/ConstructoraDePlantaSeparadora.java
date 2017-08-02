package Constructoras;

import FormaDeCalcularElCosto.IFormaDeCalcularElCosto;
import FormasDeEstimarTiempo.IFormaDeEstimarElTiempo;
import Simulador.Closure;
import Simulador.Obra;
import Simulador.PlantaSeparadora;
import Simulador.Reservorio;
import Subequipo.SubequipoDeCompraDePlantas;
import Unidades.VolumenEnMetrosCubicos;

public class ConstructoraDePlantaSeparadora extends Constructora {
	
	private SubequipoDeCompraDePlantas subequipo;

	public ConstructoraDePlantaSeparadora(IFormaDeCalcularElCosto unaFormaDeCalcularCosto,
			IFormaDeEstimarElTiempo unaFormaDeCalcularElTiempo, SubequipoDeCompraDePlantas planificador) {
		super(unaFormaDeCalcularCosto, unaFormaDeCalcularElTiempo);
		this.subequipo = planificador;
	}

	@Override
	public void empezarObra(VolumenEnMetrosCubicos unVolumenDeObra) {
		int cantidadDeDiasDeConstruccion = estimarTiempoEnDiasPara(unVolumenDeObra);
		Obra nuevaObra = new Obra(cantidadDeDiasDeConstruccion, new Closure() {
				@Override
				public void run() {
					obraTerminada(new PlantaSeparadora(subequipo.getListaTanquesAgua(),
							subequipo.getListaTanquesGas(), subequipo.getDestileria(),
							unVolumenDeObra));
				}
			});
		nuevaObra.cambiarTitulo(String.format("Planta separadora de %.2f litros", unVolumenDeObra.dameVolumen()));
		agregarObra(nuevaObra);
	}
	
	public void obraTerminada(PlantaSeparadora unaPlantaSeparadora){
		this.subequipo.plantaSeparadoraLista(unaPlantaSeparadora);
	}
}

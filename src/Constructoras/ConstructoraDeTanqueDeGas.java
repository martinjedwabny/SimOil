package Constructoras;

import FormaDeCalcularElCosto.IFormaDeCalcularElCosto;
import FormasDeEstimarTiempo.IFormaDeEstimarElTiempo;
import Simulador.Closure;
import Simulador.Obra;
import Simulador.Tanque;
import Subequipo.SubequipoDeCompraDeTanques;
import Unidades.VolumenEnMetrosCubicos;

public class ConstructoraDeTanqueDeGas extends ConstructoraDeTanque{

	private SubequipoDeCompraDeTanques planificador;

	public ConstructoraDeTanqueDeGas(IFormaDeCalcularElCosto unaFormaDeCalcularCosto,
			IFormaDeEstimarElTiempo unaFormaDeCalcularElTiempo, SubequipoDeCompraDeTanques planificador) {
		super(unaFormaDeCalcularCosto, unaFormaDeCalcularElTiempo);
		this.planificador = planificador;
	}

	@Override
	public void obraTerminada(Tanque unTanque) {
		this.planificador.tanqueDeGasListo(unTanque);
	}

	@Override
	public void empezarObra(VolumenEnMetrosCubicos unVolumenDeObra) {
		int cantidadDeDiasDeConstruccion = estimarTiempoEnDiasPara(unVolumenDeObra);
		Obra nuevaObra = new Obra(cantidadDeDiasDeConstruccion, new Closure() {
			@Override
			public void run() {
				obraTerminada(new Tanque(unVolumenDeObra));
			}
		});
		nuevaObra.cambiarTitulo(String.format("Tanque de Gas de %.2f litros", unVolumenDeObra.dameVolumen()));
		agregarObra(nuevaObra);
	}
}

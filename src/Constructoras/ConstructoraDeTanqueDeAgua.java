package Constructoras;

import FormaDeCalcularElCosto.IFormaDeCalcularElCosto;
import FormasDeEstimarTiempo.IFormaDeEstimarElTiempo;
import Simulador.Closure;
import Simulador.Obra;
import Simulador.Tanque;
import Subequipo.SubequipoDeCompraDeTanques;
import Unidades.VolumenEnMetrosCubicos;

public class ConstructoraDeTanqueDeAgua extends ConstructoraDeTanque{

	private SubequipoDeCompraDeTanques subequipo;

	public ConstructoraDeTanqueDeAgua(IFormaDeCalcularElCosto unaFormaDeCalcularCosto,
			IFormaDeEstimarElTiempo unaFormaDeCalcularElTiempo, SubequipoDeCompraDeTanques planificador) {
		super(unaFormaDeCalcularCosto, unaFormaDeCalcularElTiempo);
		this.subequipo = planificador;
	}

	@Override
	public void obraTerminada(Tanque unTanque) {
		this.subequipo.tanqueDeAguaListo(unTanque);
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
		nuevaObra.cambiarTitulo(String.format("Tanque de Agua de %.2f litros", unVolumenDeObra.dameVolumen()));
		agregarObra(nuevaObra);
	}

}

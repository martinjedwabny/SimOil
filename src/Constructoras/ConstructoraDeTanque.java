package Constructoras;

import FormaDeCalcularElCosto.IFormaDeCalcularElCosto;
import FormasDeEstimarTiempo.IFormaDeEstimarElTiempo;
import Simulador.Tanque;

public abstract class ConstructoraDeTanque extends Constructora {
	
	public ConstructoraDeTanque(IFormaDeCalcularElCosto unaFormaDeCalcularCosto,
			IFormaDeEstimarElTiempo unaFormaDeCalcularElTiempo) {
		super(unaFormaDeCalcularCosto, unaFormaDeCalcularElTiempo);
	}
	
	public abstract void obraTerminada(Tanque unTanque);
}

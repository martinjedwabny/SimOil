package CriterioDeVentaDeGas;

import java.util.ArrayList;
import java.util.List;

import Simulador.Tanque;
import Tareas.VentaDeGas;

public class CVGasNuncaVendo extends CriterioDeVentaDeGas{
	
	public CVGasNuncaVendo(float unPrecioDelMetroCubicoGas) {
		super(unPrecioDelMetroCubicoGas);
	}
	
	//Nunca vendo
	@Override
	public List<VentaDeGas> planearGas(List<Tanque> list) {
		return new ArrayList<>();
	}
}

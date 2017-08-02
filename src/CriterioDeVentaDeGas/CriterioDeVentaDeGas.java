package CriterioDeVentaDeGas;

import java.util.List;

import Simulador.Tanque;
import Tareas.VentaDeGas;

public abstract class CriterioDeVentaDeGas {
	
	final private float precioDelMetroCubicoGas;
	
	public CriterioDeVentaDeGas(float unPrecioDelMetroCubicoGas) {
		this.precioDelMetroCubicoGas = unPrecioDelMetroCubicoGas;
	}
	
	/**
	 * Ver que esto no rompa el stockDeTanquesDeGas porque viene por ref
	 * @param list
	 * @return
	 */
	public abstract List<VentaDeGas> planearGas(List<Tanque> list);

}

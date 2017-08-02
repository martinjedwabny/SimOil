package CriterioDeVentaDeGas;

import java.util.ArrayList;
import java.util.List;

import Simulador.Tanque;
import Tareas.VentaDeGas;
import Unidades.VolumenEnMetrosCubicos;

public class CVGasSiempreVendo extends CriterioDeVentaDeGas{
	
	public CVGasSiempreVendo(float unPrecioDelMetroCubicoGas) {
		super(unPrecioDelMetroCubicoGas);
	}
	
	//VENDO todo
	@Override
	public List<VentaDeGas> planearGas(List<Tanque> list) {
		List<VentaDeGas> ventas = new ArrayList<>();
		VolumenEnMetrosCubicos volumenAVender = new VolumenEnMetrosCubicos(0f);
		for (Tanque tanque : list) {
			volumenAVender = new VolumenEnMetrosCubicos(0f);
			volumenAVender.sumar(tanque.dameVolumenOcupado());
			if ( volumenAVender.dameVolumen() > 0 ){
				VentaDeGas venta = new VentaDeGas(volumenAVender, tanque);
				ventas.add(venta);
			}
		}
		return ventas;
	}
}

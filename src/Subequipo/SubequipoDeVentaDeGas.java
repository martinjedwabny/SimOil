package Subequipo;

import java.util.ArrayList;
import java.util.List;

import CriterioDeVentaDeGas.CriterioDeVentaDeGas;
import Simulador.StockDeTanques;
import Simulador.Tanque;
import Tareas.Tarea;
import Tareas.VentaDeGas;

public class SubequipoDeVentaDeGas extends Subequipo {
	
	private CriterioDeVentaDeGas criterioDeVentaDeGas;
	private StockDeTanques stockDeTanquesDeGas;
	
	public SubequipoDeVentaDeGas(CriterioDeVentaDeGas unCriterioDeVentaDeGas, 
			StockDeTanques unStockDeTanquesDeGas) {
		this.criterioDeVentaDeGas = unCriterioDeVentaDeGas;
		this.stockDeTanquesDeGas = unStockDeTanquesDeGas;
	}
	
	@Override
	public List<Tarea> realizarTareasDelDia() {
		List<Tarea> tareasDelDia = new ArrayList<>();
		List<VentaDeGas> ventas = criterioDeVentaDeGas.planearGas(stockDeTanquesDeGas.dameTanques());
		tareasDelDia.addAll(ventas);
		for(VentaDeGas venta : ventas) {
			realizarVenta(venta);
		}		
		return tareasDelDia;
	}

	private void realizarVenta(VentaDeGas venta) {
		// XXX: y el volumen de la venta no se usa ?
		Tanque tanque = venta.tanqueDeOrigen();
		tanque.extraer(tanque.dameVolumenOcupado());
	}
	
}

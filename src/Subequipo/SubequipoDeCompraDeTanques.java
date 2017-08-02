package Subequipo;

import java.util.ArrayList;
import java.util.List;

import Constructoras.ConstructoraDeTanqueDeAgua;
import Constructoras.ConstructoraDeTanqueDeGas;
import CriterioDeConstruccionDeTanques.CriterioDeConstruccionDeTanques;
import Simulador.StockDeTanques;
import Simulador.Tanque;
import Tareas.PedidoTanque;
import Tareas.Tarea;

public class SubequipoDeCompraDeTanques extends Subequipo{

	private CriterioDeConstruccionDeTanques criterioDeConstruccionDeTanques;
	private StockDeTanques tanquesDeAgua;
	private StockDeTanques tanquesDeGas;
	private ConstructoraDeTanqueDeAgua constructoraDeTanqueDeAgua;
	private ConstructoraDeTanqueDeGas constructoraDeTanqueDeGas;
	
	public SubequipoDeCompraDeTanques(CriterioDeConstruccionDeTanques criterioDeConstruccionDeTanques,
			StockDeTanques tanquesDeAgua, StockDeTanques tanquesDeGas,
			ConstructoraDeTanqueDeAgua constructoraDeTanqueDeAgua,
			ConstructoraDeTanqueDeGas constructoraDeTanqueDeGas) {

		this.criterioDeConstruccionDeTanques = criterioDeConstruccionDeTanques;
		this.tanquesDeAgua = tanquesDeAgua;
		this.tanquesDeGas = tanquesDeGas;
		this.constructoraDeTanqueDeAgua = constructoraDeTanqueDeAgua;
		this.constructoraDeTanqueDeGas = constructoraDeTanqueDeGas;
	}
	
	public SubequipoDeCompraDeTanques(CriterioDeConstruccionDeTanques criterioDeConstruccionDeTanques,
			StockDeTanques tanquesDeAgua, StockDeTanques tanquesDeGas) {

		this.criterioDeConstruccionDeTanques = criterioDeConstruccionDeTanques;
		this.tanquesDeAgua = tanquesDeAgua;
		this.tanquesDeGas = tanquesDeGas;
	}
	
	public void cambiarConstructoraDeTanquesDeAgua(ConstructoraDeTanqueDeAgua constructoraDeTanqueDeAgua) {
		this.constructoraDeTanqueDeAgua = constructoraDeTanqueDeAgua;
	}
	
	public void cambiarConstructoraDeTanquesDeGas(ConstructoraDeTanqueDeGas constructoraDeTanqueDeGas) {
		this.constructoraDeTanqueDeGas = constructoraDeTanqueDeGas;
	}
	
	@Override
	public List<Tarea> realizarTareasDelDia() {
		List<Tarea> tareasDelDia = new ArrayList<Tarea>();
		
		List<PedidoTanque> pedidosTanques = criterioDeConstruccionDeTanques.planearTanques(
				tanquesDeAgua.dameTanques(), 
				tanquesDeGas.dameTanques(),
				constructoraDeTanqueDeAgua,
				constructoraDeTanqueDeGas);
		tareasDelDia.addAll(pedidosTanques);
		for(PedidoTanque pedido : pedidosTanques) {
			realizarPedido(pedido);
		}
		
		return tareasDelDia;
	}

	private void realizarPedido(PedidoTanque pedido) {
		pedido.constructora().empezarObra(pedido.volumenAConstruir());
	}

	public void tanqueDeAguaListo(Tanque unTanque) {
		tanquesDeAgua.agregarTanque(unTanque);
	}

	public void tanqueDeGasListo(Tanque unTanque) {
		tanquesDeGas.agregarTanque(unTanque);
	}
}

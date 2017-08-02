package Subequipo;

import java.util.ArrayList;
import java.util.List;

import Constructoras.ConstructoraDePlantaSeparadora;
import CriterioDeConstruccionDePlantas.CriterioDeConstruccionDePlantas;
import Simulador.Destileria;
import Simulador.PlantaSeparadora;
import Simulador.Reservorio;
import Simulador.StockDePlantasSeparadoras;
import Simulador.StockDeTanques;
import Tareas.PedidoPlanta;
import Tareas.Tarea;

public class SubequipoDeCompraDePlantas extends Subequipo {
	
	StockDePlantasSeparadoras listaPlantas;
	StockDeTanques listaTanquesAgua;
	StockDeTanques listaTanquesGas;
	CriterioDeConstruccionDePlantas criterioDeConstruccionDePlantas;
	ConstructoraDePlantaSeparadora constructoraDePlantas;
	Destileria destileria;
	
	public SubequipoDeCompraDePlantas(StockDePlantasSeparadoras listaPlantas,
			StockDeTanques listaTanquesGas,
			StockDeTanques listaTanquesAgua,
			CriterioDeConstruccionDePlantas criterioDeConstruccionDePlantas,
			ConstructoraDePlantaSeparadora constructoraDePlantas,
			Destileria unaDestileria) {
		this.destileria = unaDestileria;
		this.listaPlantas = listaPlantas;
		this.listaTanquesGas = listaTanquesGas;
		this.listaTanquesAgua = listaTanquesAgua;
		this.criterioDeConstruccionDePlantas = criterioDeConstruccionDePlantas;
		this.constructoraDePlantas = constructoraDePlantas;
	}

	public SubequipoDeCompraDePlantas(StockDePlantasSeparadoras listaPlantas,
			StockDeTanques listaTanquesGas,
			StockDeTanques listaTanquesAgua,
			CriterioDeConstruccionDePlantas criterioDeConstruccionDePlantas,
			Destileria unaDestileria) {
		this.listaPlantas = listaPlantas;
		this.listaTanquesGas = listaTanquesGas;
		this.listaTanquesAgua = listaTanquesAgua;
		this.criterioDeConstruccionDePlantas = criterioDeConstruccionDePlantas;
		this.destileria = unaDestileria;
	}
	
	public void cambiarConstructoraDePlantas(ConstructoraDePlantaSeparadora unaConstructora) {
		this.constructoraDePlantas = unaConstructora;
	}

	@Override
	public List<Tarea> realizarTareasDelDia() {
		List<Tarea> tareasDelDia = new ArrayList<Tarea>();
		
		List<PedidoPlanta> pedidosPlantas = criterioDeConstruccionDePlantas.planearPlantas(listaPlantas.damePlantasSeparadoras(),
				listaTanquesGas.dameTanques(),
				listaTanquesAgua.dameTanques(),
				constructoraDePlantas);
		tareasDelDia.addAll(pedidosPlantas);
		for(PedidoPlanta pedido : pedidosPlantas) {
			realizarPedido(pedido);
		}
		
		return tareasDelDia;
	}

	private void realizarPedido(PedidoPlanta pedido) {
		pedido.constructora().empezarObra(pedido.volumenAConstruir());
	}

	public void plantaSeparadoraLista(PlantaSeparadora unaPlantaSeparadora) {
		listaPlantas.agregarPlantaSeparadora(unaPlantaSeparadora);
	}

	public Destileria dameDestileria() {
		return this.destileria;
	}
	
	public StockDeTanques getListaTanquesAgua() {
		return listaTanquesAgua;
	}

	public StockDeTanques getListaTanquesGas() {
		return listaTanquesGas;
	}
	
	public Destileria getDestileria() {
		return this.destileria;
	}
}

package Subequipo;

import java.util.ArrayList;
import java.util.List;

import CriterioDeSeleccionDeParcelasConRIGs.CriterioDeSeleccionDeParcelasConRIGs;
import CriterioDeSeleccionDeParcelasConRIGs.CriterioDeSeleccionDeParcelasConRIGs.AsignacionParcelaRIG;
import ProveedoraDeRigs.ProveedoraDeRIGs;
import ProveedoraDeRigs.RIG;
import Simulador.ExcavacionEnProgreso;
import Simulador.Parcela;
import Simulador.Pozo;
import Simulador.Reservorio;
import Simulador.StockDePlantasSeparadoras;
import Tareas.PedidoRIG;
import Tareas.Tarea;
import Tareas.TareaExcavacion;

public class SubequipoDeExcavacion extends Subequipo {

	private List<ExcavacionEnProgreso> excavaciones;
	private CriterioDeSeleccionDeParcelasConRIGs criterioDeSeleccionDeParcelasConRIGs;
	private ProveedoraDeRIGs proveedoraDeRIGs;
	private List<Parcela> parcelas;
	private List<Pozo> pozos;
	private Reservorio reservorio;
	
	/**
	 * 
	 * @param unaProveedoraDeRIGs
	 * @param unCriterioDeSeleccionDeParcelasConRIGs
	 * @param unaListaDeExcavacionesDeParcelas
	 * @param unCriterioDeAlquilerDeRIGs
	 * @param unEquipoDeExcavacion
	 * @param unStockDePlantasSeparadoras
	 */
	public SubequipoDeExcavacion(ProveedoraDeRIGs unaProveedoraDeRIGs,
			CriterioDeSeleccionDeParcelasConRIGs unCriterioDeSeleccionDeParcelasConRIGs,
			List<Parcela> parcelas,
			List<Pozo> pozos,
			Reservorio reservorio) {
		
		this.proveedoraDeRIGs = unaProveedoraDeRIGs;
		this.criterioDeSeleccionDeParcelasConRIGs = unCriterioDeSeleccionDeParcelasConRIGs;
		this.parcelas = parcelas;
		this.excavaciones = new ArrayList<>();
		this.pozos = pozos;
		this.reservorio = reservorio;
	}
	
	@Override
	public List<Tarea> realizarTareasDelDia() {
		List<Tarea> tareasDelDia = new ArrayList<Tarea>();
		
		List<AsignacionParcelaRIG> asignaciones = 
				criterioDeSeleccionDeParcelasConRIGs.planearAsignaciones(parcelas, proveedoraDeRIGs);
		
		List<RIG> alquilados = proveedoraDeRIGs.dameRIGsAlquilados();
		for(AsignacionParcelaRIG asignacion : asignaciones) {
			RIG rigAsignado = asignacion.dameRIG();
			Parcela parcelaAsignada = asignacion.dameParcela();
			
			if (!alquilados.contains(rigAsignado)) {
				PedidoRIG pedidoRIG = new PedidoRIG(rigAsignado, proveedoraDeRIGs);
				realizarPedido(pedidoRIG);
				tareasDelDia.add(pedidoRIG);
			}
			
			TareaExcavacion tareaExcavacion = new TareaExcavacion(parcelaAsignada, rigAsignado);
			realizarExcavacion(tareaExcavacion);
			tareasDelDia.add(tareaExcavacion);
		}
		
		return tareasDelDia;
	}

	private void realizarPedido(PedidoRIG pedidoRIG) {
		pedidoRIG.proveedoraDeRIGs().alquilar(pedidoRIG.rigAAlquilar(), pedidoRIG.cantidadDeDiasDeAlquiler());
	}
	
	private ExcavacionEnProgreso obtenerExcavacionEnProgresoPara(Parcela parcela) {
		for (ExcavacionEnProgreso excavacion : excavaciones) {
			if (excavacion.parcelaExcavando() == parcela) {
				return excavacion;
			}
		}
		return null;
	}

	private void realizarExcavacion(TareaExcavacion tarea) {
		Parcela parcela = tarea.parcelaAExcavar();
		
		ExcavacionEnProgreso excavacionARealizar = obtenerExcavacionEnProgresoPara(parcela);		
		
		if (excavacionARealizar == null) {
			excavacionARealizar = new ExcavacionEnProgreso(parcela,proveedoraDeRIGs.damePrecioCompraCombustible());
			excavaciones.add(excavacionARealizar);
		}
		excavacionARealizar.trabajarCon(tarea.rigAUtilizar());
		
		if(excavacionARealizar.termino()) {
			parcelas.remove(parcela);
			excavaciones.remove(excavacionARealizar);
			Pozo pozo = new Pozo(parcela, reservorio);
			pozos.add(pozo);
		}
	}
	
	
}

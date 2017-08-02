package Subequipo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CriterioDeCompraDeAgua.CriterioDeCompraDeAgua;
import CriterioDeExtraccion.CriterioDeExtraccion;
import CriterioDeReinyeccion.CriterioDeReinyeccion;
import Simulador.Pozo;
import Simulador.Reservorio;
import Simulador.StockDePlantasSeparadoras;
import Simulador.StockDeTanques;
import Simulador.Tanque;
import Tareas.CompraDeAgua;
import Tareas.Tarea;
import Tareas.TareaExtraccion;
import Tareas.TareaReinyeccion;
import Unidades.CompuestoGasAguaPetroleo;
import Unidades.PresionEnHectopascales;
import Unidades.VolumenEnMetrosCubicos;

public class SubequipoDeExtraccionYReinyeccion extends Subequipo {
	
	private StockDeTanques tanquesDeAgua;
	private StockDeTanques tanquesDeGas;
	private StockDePlantasSeparadoras plantasSeparadoras;
	
	private List<Pozo> pozos;
	
	private Reservorio reservorio;
	private CriterioDeExtraccion criterioDeExtraccion;
	private CriterioDeCompraDeAgua criterioDeCompraDeAgua;
	private CriterioDeReinyeccion criterioDeReinyeccion;
	
	private VolumenEnMetrosCubicos volumenGlobalExtraido;
	private VolumenEnMetrosCubicos volumenGlobalReinyectado;
	
	public VolumenEnMetrosCubicos getVolumenGlobalExtraido() {
		return volumenGlobalExtraido;
	}

	public VolumenEnMetrosCubicos getVolumenGlobalReinyectado() {
		return volumenGlobalReinyectado;
	}

	/**
	 * 
	 * @param plantasSeparadoras
	 * @param unStockDeTanquesDeAgua
	 * @param unStockDeTanquesDeGas
	 * @param unaListaDePresionesDePozos
	 * @param unCriterioDeExtraccion
	 * @param unCriterioDeCompraDeAgua
	 * @param unCriterioDeReinyeccion
	 * @param reservorio
	 */
	public SubequipoDeExtraccionYReinyeccion(StockDePlantasSeparadoras plantasSeparadoras, StockDeTanques unStockDeTanquesDeAgua, StockDeTanques unStockDeTanquesDeGas, 
			List<Pozo> pozos, CriterioDeExtraccion unCriterioDeExtraccion, 
			CriterioDeCompraDeAgua unCriterioDeCompraDeAgua,
			CriterioDeReinyeccion unCriterioDeReinyeccion,
			Reservorio reservorio) {
		
		this.plantasSeparadoras = plantasSeparadoras;
		this.tanquesDeAgua = unStockDeTanquesDeAgua;
		this.tanquesDeGas = unStockDeTanquesDeGas;
		this.pozos = pozos;
		this.criterioDeExtraccion = unCriterioDeExtraccion;
		this.criterioDeReinyeccion = unCriterioDeReinyeccion;
		this.criterioDeCompraDeAgua = unCriterioDeCompraDeAgua;
		this.volumenGlobalExtraido = new VolumenEnMetrosCubicos(0f);
		this.volumenGlobalReinyectado = new VolumenEnMetrosCubicos(0f);
		this.reservorio = reservorio;
	}
	
	@Override
	public List<Tarea> realizarTareasDelDia() {
		List<Tarea> tareasDelDia = new ArrayList<Tarea>();
		
		CompraDeAgua compraAgua = this.criterioDeCompraDeAgua.planearCompraDeAgua(tanquesDeAgua);
		if (compraAgua != null) {			
			tareasDelDia.add(compraAgua);
			float volumenAUsar = compraAgua.volumenDeCompra().dameVolumen();
			
			// pongo el agua comprada en los tanques
			for(Tanque t : tanquesDeAgua.dameTanques()) {
				float volRestante = t.dameVolumenMaximo().dameVolumen() - t.dameVolumenOcupado().dameVolumen();
				
				float volALLenar = Math.min(volumenAUsar, volRestante);
				t.almacenar(new VolumenEnMetrosCubicos(volALLenar));
				
				volumenAUsar = volumenAUsar - volALLenar;
				if (volumenAUsar < 0.1) { // no hay más agua, y hay que evitar errores de punto flotante
					break;
				}
			}
		}
		
		
		List<TareaReinyeccion> tareasDeReinyeccion = new ArrayList<>();
		tareasDeReinyeccion.addAll(criterioDeReinyeccion.planearReinyeccion(
				volumenGlobalExtraido, volumenGlobalReinyectado, pozos, tanquesDeAgua));
		
		//Si se reinyecta no se extrae
		if(!tareasDeReinyeccion.isEmpty()) {
			tareasDelDia.addAll(tareasDeReinyeccion);
			for (TareaReinyeccion tarea : tareasDeReinyeccion) {
				realizarReinyeccion(tarea);
			}
		} else {
			List<TareaExtraccion> tareasExtraccion = criterioDeExtraccion.planearExtraccion(pozos, plantasSeparadoras, reservorio);
			tareasDelDia.addAll(tareasExtraccion);
			for (TareaExtraccion tarea : tareasExtraccion) {
				realizarExtraccion(tarea);
			}
			if (tareasExtraccion.size() > 0) {				
				actualizarPresionEnPozosExtraccion();
			}
		}
		
		return tareasDelDia;
	}

	private void realizarReinyeccion(TareaReinyeccion tarea) {
		VolumenEnMetrosCubicos volumenTotal = tarea.volumenAReinyectar();
		tarea.tanqueDeDondeSacarAgua().extraer(volumenTotal);
		this.volumenGlobalReinyectado.sumar(volumenTotal);
		actualizarPresionEnPozoReinyeccion(tarea.pozoAReinyectar());
		recalcularCompuestoEnReservorio();
		float nuevoVol = this.reservorio.dameVolumenActual().dameVolumen() + tarea.volumenAReinyectar().dameVolumen();
		this.reservorio.setVolumenActual(new VolumenEnMetrosCubicos(nuevoVol));
	}
	
	private void recalcularCompuestoEnReservorio(){
		//calcular el nuevo compuesto;
		CompuestoGasAguaPetroleo compuestoActual = reservorio.dameCompuesto();
		
		float porcentajePetroleo = compuestoActual.damePorcentajeDePetroleoSobre100();
		float porcentajeGas = compuestoActual.damePorcentajeDeGasSobre100();
		float porcentajeAgua = compuestoActual.damePorcentajeDeAguaSobre100();
		
		float volumenActual = reservorio.dameVolumenActual().dameVolumen();
		
		float nuevoPorcentajePetroleo = porcentajePetroleo *
				(volumenActual - getVolumenGlobalExtraido().dameVolumen()) /
				(volumenActual - getVolumenGlobalExtraido().dameVolumen() + getVolumenGlobalReinyectado().dameVolumen());
		
		float nuevoPorcentajeGas = porcentajeGas *
				(volumenActual - getVolumenGlobalExtraido().dameVolumen()) /
				(volumenActual - getVolumenGlobalExtraido().dameVolumen() + getVolumenGlobalReinyectado().dameVolumen());
		
		float nuevoPorcentajeAgua = (porcentajeAgua *
				(volumenActual - getVolumenGlobalExtraido().dameVolumen()) +
				100 * getVolumenGlobalReinyectado().dameVolumen()) /
				(volumenActual - getVolumenGlobalExtraido().dameVolumen() + getVolumenGlobalReinyectado().dameVolumen());
		
		CompuestoGasAguaPetroleo nuevoCompuesto = new CompuestoGasAguaPetroleo(nuevoPorcentajeGas,
				nuevoPorcentajeAgua, nuevoPorcentajePetroleo);
		
		reservorio.actualizarCompuesto(nuevoCompuesto);
	}

	private void realizarExtraccion(TareaExtraccion tarea) {
		tarea.pozoAExtraer().extraer(tarea.volumenAExtraer(), tarea.plantaSeparadoraQueRecibeElVolumen());
		this.volumenGlobalExtraido.sumar(tarea.volumenAExtraer());
		float nuevoVol = this.reservorio.dameVolumenActual().dameVolumen() - tarea.volumenAExtraer().dameVolumen();
		this.reservorio.setVolumenActual(new VolumenEnMetrosCubicos(nuevoVol));
	}

	private void actualizarPresionEnPozosExtraccion() {
		float VolRi = reservorio.dameVolumenActual().dameVolumen();
		float VolR = this.reservorio.dameVolumenInicial().dameVolumen();
		int N = this.pozos.size();
		double Bi = 0.1 * (VolRi / VolR) / Math.cbrt(Math.pow((double)N, 2.0));
		for (Pozo p : this.pozos) {
			double presion = p.presionActual().damePresion();
			double nuevaPresion = presion * Math.exp(-Bi);
			p.actualizarPresion(new PresionEnHectopascales(nuevaPresion));
		}
	}

	public void actualizarPresionEnPozoReinyeccion(Pozo unPozo) {
		PresionEnHectopascales nuevaPresion =  unPozo.presionInicial();
		
		float VolR = this.reservorio.dameVolumenInicial().dameVolumen();
		float VolGlobalExtraido = this.volumenGlobalExtraido.dameVolumen();
		float VolGlobalReinyectado = this.volumenGlobalReinyectado.dameVolumen();
				
		nuevaPresion = nuevaPresion.multiplicar( (VolR-VolGlobalExtraido+VolGlobalReinyectado) 
				/ VolR);
		
		unPozo.actualizarPresion(nuevaPresion);
	}

	public Reservorio dameReservorio() {
		return reservorio;
	}
	
	/**
	 * La cota maxima dada por el reservorio (enunciado).
	 * @return
	 */
	private VolumenEnMetrosCubicos maximoVolumenReinyectable(){
		VolumenEnMetrosCubicos maximoVolumenAReinyectar = new VolumenEnMetrosCubicos(this.volumenGlobalExtraido);
		maximoVolumenAReinyectar.restar(this.volumenGlobalReinyectado);
		return maximoVolumenAReinyectar;
	}
	
	/**
	 * 
	 * @param pozo
	 * @return
	 */
	public Map<Tanque,VolumenEnMetrosCubicos> tanquesYVolumenesParaReinyectarEn(Pozo pozo) {
		Map<Tanque,VolumenEnMetrosCubicos> result = new HashMap<Tanque, VolumenEnMetrosCubicos>();
		
		VolumenEnMetrosCubicos volMax = maximoVolumenReinyectable();
		VolumenEnMetrosCubicos sumaVolAguaYGas = tanquesDeAgua.totalVolumen();
		sumaVolAguaYGas.sumar(tanquesDeGas.totalVolumen());
		
		
		VolumenEnMetrosCubicos volAExtraer = new VolumenEnMetrosCubicos( 
				Math.min(volMax.dameVolumen(), sumaVolAguaYGas.dameVolumen()) );
		
		result.putAll( tanquesDeAgua.extraerDeTanquesHasta(volAExtraer) );
		result.putAll( tanquesDeGas.extraerDeTanquesHasta(volAExtraer) );
		
		return result;
	}

}

package Simulador;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import ProveedoraDeRigs.RIG;
import Tareas.CompraDeAgua;
import Tareas.PedidoPlanta;
import Tareas.PedidoRIG;
import Tareas.PedidoTanque;
import Tareas.TareaExcavacion;
import Tareas.TareaExtraccion;
import Tareas.TareaReinyeccion;
import Tareas.VentaDeGas;
import Tareas.VentaDePetroleo;
import Unidades.VolumenEnMetrosCubicos;

public class RegistradorTarea {
	
	private float costos;
	private float ventas;
	private float precioCompraCombustible;
	private float precioCompraAgua;
	private float precioVentaGas;
	private float precioVentaPetroleo;
	private StockDePlantasSeparadoras plantasSeparadoras;
	private StockDeTanques tanquesDeAgua;
	private StockDeTanques tanquesDeGas;
	private List<Pozo> pozos;
	private Reservorio reservorio;
	private Dictionary<PlantaSeparadora, VolumenEnMetrosCubicos> volumenSeparadoHoy;
	
	public RegistradorTarea(
			float precioCompraCombustible, 
			float precioCompraAgua,
			float precioVentaGas, 
			float precioVentaPetroleo, 
			StockDePlantasSeparadoras plantasSeparadoras, 
			StockDeTanques tanquesDeAgua, 
			StockDeTanques tanquesDeGas, 
			List<Pozo> pozos, 
			Reservorio reservorio) {
		this.precioCompraCombustible = precioCompraCombustible;
		this.precioCompraAgua = precioCompraAgua;
		this.precioVentaGas = precioVentaGas;
		this.precioVentaPetroleo = precioVentaPetroleo;
		this.plantasSeparadoras = plantasSeparadoras;
		this.tanquesDeAgua = tanquesDeAgua;
		this.tanquesDeGas = tanquesDeGas;
		this.pozos = pozos;
		this.reservorio = reservorio;
		this.volumenSeparadoHoy = new Hashtable<PlantaSeparadora, VolumenEnMetrosCubicos>();
		
		this.costos = 0f;
		this.ventas = 0f;
	}
	
	public float getCostos() {
		return costos;
	}
	
	public float getVentas() {
		return ventas;
	}
	
	public float getSaldo() {
		return ventas - costos;
	}
	
	public void registrarExtraccion(TareaExtraccion extraccion) {
		if (extraccion.volumenAExtraer().dameVolumen() == 0f) return;
		System.out.println(String.format("Extracción de volumen: %.10f litros, en Pozo: %d",
				extraccion.volumenAExtraer().dameVolumen(),
				extraccion.pozoAExtraer().hashCode()));
		volumenSeparadoHoy.put(extraccion.plantaSeparadoraQueRecibeElVolumen(), extraccion.volumenAExtraer());
	}

	public void registrarExcavacion(TareaExcavacion excavacion) {
		RIG rig = excavacion.rigAUtilizar();
		float costoCombustible = rig.dameConsumoDiarioEnLitros() * precioCompraCombustible;
		costos += costoCombustible;
	}
	
	public void registrarReinyeccion(TareaReinyeccion reinyeccion) {
		if (reinyeccion.volumenAReinyectar().dameVolumen() == 0) return;
		System.out.println(
				String.format("Reinyección en Pozo: %d de volumen %.2f litros (sacados de Tanque: %d)",
				reinyeccion.pozoAReinyectar().hashCode(),
				reinyeccion.volumenAReinyectar().dameVolumen(),
				reinyeccion.tanqueDeDondeSacarAgua().hashCode()));
	}

	public void registrarPedidoPlanta(PedidoPlanta pedido) {
		float costoPedido = pedido.constructora().cotizarParaUn(pedido.volumenAConstruir());
		costos += costoPedido;
		System.out.println(
				String.format("Se compra una Planta. Tiempo para finalizar: %d dias. El costo del pedido es: $ %.2f",
				pedido.constructora().estimarTiempoEnDiasPara(pedido.volumenAConstruir()),
				costoPedido));
	}

	public void registrarPedidoTanque(PedidoTanque pedido) {
		float costoPedido = pedido.constructora().cotizarParaUn(pedido.volumenAConstruir());
		costos += costoPedido;
		System.out.println(String.format("Se compro un Tanque. Tiempo para finalizar: %d dias. Costo: %.2f",
				pedido.constructora().estimarTiempoEnDiasPara(pedido.volumenAConstruir()),
				costoPedido));
	}

	public void registrarCompraDeAgua(CompraDeAgua compra) {
		float costoCompra = compra.volumenDeCompra().dameVolumen() * precioCompraAgua;
		this.costos += costoCompra;
		System.out.println(
				String.format("Compra de Agua: %.2f litros. Costo: $%.2f",
				compra.volumenDeCompra().dameVolumen(),
				costoCompra));
	}

	public void registrarVentaDeGas(VentaDeGas venta) {
		float gananciasVenta = venta.volumenDeVenta().dameVolumen() * precioVentaGas;
		this.ventas += gananciasVenta;
		System.out.println(
				String.format("Venta de Gas: %.2f litros, por: $ %.2f",
				venta.volumenDeVenta().dameVolumen(),
				gananciasVenta));
	}

	public void registrarPedidoRIG(PedidoRIG pedido) {
		float costoAlquiler = pedido.proveedoraDeRIGs().precioAlquierDiarioDe(pedido.rigAAlquilar()) * pedido.cantidadDeDiasDeAlquiler();
		costos += costoAlquiler;
		System.out.println(
				String.format("Pedido de RIG para %d dias, costo: $ %.2f",
				pedido.cantidadDeDiasDeAlquiler(),
				costoAlquiler));
	}

	public void registrarVentaDePetroleo(VentaDePetroleo ventaDePetroleo) {
		float gananciasDia = ventaDePetroleo.dameGanancias(precioVentaPetroleo);
		ventas += gananciasDia;
		System.out.println(
				String.format("Venta de Pétroleo: $ %.2f",
				gananciasDia));
	}

	public void registrarNuevoDia(int dia) {
		System.out.println(String.format("\n-------------------------------------------------- Dia %d --------------------------------------------------\n", dia));
	}
	
	public void registrarEstadoGeneral() {
		System.out.println(String.format("Estado general: "));
		
		// plantas separadoras
		System.out.println(String.format("- Hay %d Plantas Separadoras: (formato <Id, Volumen Procesado Hoy>)", plantasSeparadoras.damePlantasSeparadoras().size()));
		for (PlantaSeparadora planta: plantasSeparadoras.damePlantasSeparadoras()) {
			float separadoHoyPlanta = this.volumenSeparadoHoy.get(planta) == null ? 0f : this.volumenSeparadoHoy.get(planta).dameVolumen();
			System.out.println(String.format("-- <Planta %d, %.2f litros, %.2f litros>",
					planta.hashCode(),
					planta.dameProcesamientoMaximoDiario().dameVolumen(),
					separadoHoyPlanta));
		}
		this.volumenSeparadoHoy = new Hashtable<PlantaSeparadora, VolumenEnMetrosCubicos>();
		
		// tanques de agua
		if (tanquesDeAgua.dameTanques().isEmpty()) {			
			System.out.println(String.format("- Hay 0 Tanques de Agua"));
		} else {
			System.out.println(String.format("- Hay %d Tanques de Agua con capacidad maxima %.2f litros, y volumen ocupado:",
					tanquesDeAgua.dameTanques().size(), tanquesDeAgua.dameTanques().get(0).dameVolumenMaximo().dameVolumen()));
			System.out.print("[");
			for (Tanque tanque: tanquesDeAgua.dameTanques()) {
				System.out.print(String.format("%.2f, ",
						tanque.dameVolumenOcupado().dameVolumen()));
			}
			System.out.println("]");
		}
		
		// tanques de gas
		if (tanquesDeGas.dameTanques().isEmpty()) {			
			System.out.println(String.format("- Hay 0 Tanques de Gas"));
		} else {
			System.out.println(String.format("- Hay %d Tanques de Gas con capacidad maxima %.2f litros, y volumen ocupado:",
					tanquesDeGas.dameTanques().size(), tanquesDeGas.dameTanques().get(0).dameVolumenMaximo().dameVolumen()));
			System.out.print("[");
			for (Tanque tanque: tanquesDeGas.dameTanques()) {
				System.out.print(String.format("%.2f, ",
						tanque.dameVolumenOcupado().dameVolumen()));
			}
			System.out.println("]");
		}
		
		// pozos
		System.out.println(String.format("- Hay %d Pozos: (formato <Id, Presion actual en PSIA, Presion inicial en PSIA>)", pozos.size()));
		for (Pozo pozo: pozos) {
			System.out.println(String.format("-- <Pozo %d, %.2f, %.2f>",
					pozo.hashCode(),
					pozo.presionActual().damePresion(),
					pozo.presionInicial().damePresion()));
		}
		
		// reservorio
		System.out.println(String.format("- El reservorio tiene un compuesto actual: (petroleo: %.10f, agua: %.10f, gas: %.10f) y un volumen actual: %.2f (volumen inicial: %.2f)",
				reservorio.dameCompuesto().damePorcentajeDePetroleoSobre100(),
				reservorio.dameCompuesto().damePorcentajeDeAguaSobre100(),
				reservorio.dameCompuesto().damePorcentajeDeGasSobre100(),
				reservorio.dameVolumenActual().dameVolumen(),
				reservorio.dameVolumenInicial().dameVolumen()));
	}

	public void registrarFinalizacion() {
		System.out.println(String.format("\n---> Terminó\n"));
		System.out.println(String.format("Costos totales: $ %.2f", costos));
		System.out.println(String.format("Ventas totales: $ %.2f", ventas));
		float saldo = getSaldo();
		System.out.println(String.format("Saldo total: $ %.2f", saldo));
	}
}

package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Constructoras.ConstructoraDePlantaSeparadora;
import Constructoras.ConstructoraDeTanqueDeAgua;
import Constructoras.ConstructoraDeTanqueDeGas;
import CriterioDeCompraDeAgua.CriterioDeCompraDeAgua;
import CriterioDeCompraDeAgua.CriterioDeCompraDeAguaPorPorcentajeLleno;
import CriterioDeCompraDeAgua.CriterioDeCompraDeAguaFijoPorDia;
import CriterioDeConstruccionDePlantas.CCPlantasCantidadFija;
import CriterioDeConstruccionDePlantas.CCPlantasUnaNuevaPorDia;
import CriterioDeConstruccionDePlantas.CriterioDeConstruccionDePlantas;
import CriterioDeConstruccionDeTanques.CCTanquesCantidadFija;
import CriterioDeConstruccionDeTanques.CCTanquesPorNecesidad;
import CriterioDeConstruccionDeTanques.CCTanquesUnoNuevoPorDia;
import CriterioDeConstruccionDeTanques.CriterioDeConstruccionDeTanques;
import CriterioDeExtraccion.CriterioDeExtraccion;
import CriterioDeExtraccion.CriterioDeExtraccionRandom;
import CriterioDeExtraccion.CriterioDeExtraccionSacoLoMasQuePueda;
import CriterioDeReinyeccion.CriterioDeReinyeccion;
import CriterioDeReinyeccion.CriterioDeReinyeccionCantidadDeLitrosFijosCuandoLaPresionEsMenor;
import CriterioDeReinyeccion.CriterioDeReinyeccionLoMasQuePueda;
import CriterioDeSeleccionDeParcelasConRIGs.CSPRPriorizandoParcelasConMayorPresionYRIGsBaratos;
import CriterioDeSeleccionDeParcelasConRIGs.CSPRPriorizandoParcelasConMayorPresionYRIGsPotentes;
import CriterioDeSeleccionDeParcelasConRIGs.CSPRPriorizandoParcelasConMenorDurezaYRIGsBaratos;
import CriterioDeSeleccionDeParcelasConRIGs.CSPRPriorizandoParcelasConMenorDurezaYRIGsPotentes;
import CriterioDeSeleccionDeParcelasConRIGs.CriterioDeSeleccionDeParcelasConRIGs;
import CriterioDeTerminacion.CTPorDilucionCritica;
import CriterioDeTerminacion.CriterioDeTerminacion;
import CriterioDeTerminacion.CriterioDeTerminacionFijo;
import CriterioDeTerminacion.CriterioDeTerminacionRandom;
import CriterioDeVentaDeGas.CVGasNuncaVendo;
import CriterioDeVentaDeGas.CVGasSiempreVendo;
import CriterioDeVentaDeGas.CriterioDeVentaDeGas;
import FormaDeCalcularElCosto.FDCCCuadraticoAlVolumen;
import FormaDeCalcularElCosto.FDCCLinealAlVolumen;
import FormaDeCalcularElCosto.IFormaDeCalcularElCosto;
import FormasDeEstimarTiempo.FDETCuadraticoAlVolumen;
import FormasDeEstimarTiempo.FDETLinealAlVolumen;
import FormasDeEstimarTiempo.IFormaDeEstimarElTiempo;
import ProveedoraDeRigs.ProveedoraDeRIGs;
import ProveedoraDeRigs.RIG;
import Simulador.AvanzadorDeDias;
import Simulador.Destileria;
import Simulador.EquipoDeIngenieria;
import Simulador.Parcela;
import Simulador.Pozo;
import Simulador.RegistradorTarea;
import Simulador.Reservorio;
import Simulador.StockDePlantasSeparadoras;
import Simulador.StockDeTanques;
import Subequipo.Subequipo;
import Subequipo.SubequipoDeCompraDePlantas;
import Subequipo.SubequipoDeCompraDeTanques;
import Subequipo.SubequipoDeExcavacion;
import Subequipo.SubequipoDeExtraccionYReinyeccion;
import Subequipo.SubequipoDeVentaDeGas;
import Subequipo.SubequipoDeVentaDePetroleo;
import Unidades.CompuestoGasAguaPetroleo;
import Unidades.PresionEnHectopascales;
import Unidades.VolumenEnMetrosCubicos;

public class UI {

	private JFrame frmSimoil;
	private JTextField txtAlpha1;
	private JTextField txtVentaPetroleo;
	private JTextField txtAlpha2;
	private JTextField txtVentaGas;
	private JTextField txtCompraAgua;
	private JTextField txtCompraCombustible;
	private JTextField txtDilucionCritica;
	
	/**
	 * Objetos del simulador
	 */
	
	private static Reservorio reservorio;
	private static ArrayList<Parcela> parcelas;
	private static float alpha1;
	private static float alpha2;
	private static Float valorVentaGas;
	private static Float valorVentaPetroleo;
	private static Float valorCompraAgua;
	private static Float valorCompraCombustible;
	private static Float volumenInicial;
	private static Float dilucionCritica;
	private static ArrayList<Subequipo> subequipos;
	private static CriterioDeTerminacion unCriterioTerminacion;
	private static IFormaDeCalcularElCosto unaFormaDeCalcularCostoGas;
	private static IFormaDeEstimarElTiempo unaFormaDeCalcularElTiempoGas;
	private static SubequipoDeCompraDeTanques planificadorTanques;
	private static IFormaDeCalcularElCosto unaFormaDeCalcularCostoAgua;
	private static IFormaDeEstimarElTiempo unaFormaDeCalcularElTiempoAgua;
	private static IFormaDeCalcularElCosto unaFormaDeCalcularCostoPlanta;
	private static IFormaDeEstimarElTiempo unaFormaDeCalcularElTiempoPlanta;
	private static SubequipoDeCompraDePlantas planificadorPlanta;
	private static Map<RIG, Float> unaListaDePreciosDeAlquilerDeRIGs;
	private static Map<RIG, Integer> unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs;
	private static StockDeTanques tanquesDeAgua;
	private static StockDeTanques tanquesDeGas;
	private static CriterioDeConstruccionDeTanques unCriterioDeConstruccionTanques;
	private static Destileria destileria;
	private static StockDePlantasSeparadoras plantasSeparadoras;
	private static CriterioDeConstruccionDePlantas unCriterioDeConstruccionDePlantas;
	private static CriterioDeExtraccion unCriterioDeExtraccion;
	private static CriterioDeCompraDeAgua unCriterioDeCompraDeAgua;
	private static CriterioDeReinyeccion unCriterioDeReinyeccion;
	private static CriterioDeSeleccionDeParcelasConRIGs unCriterioDeSeleccionDeParcelasConRIGs;
	private static float unPrecioDelMetroCubicoGas;
	private static CriterioDeVentaDeGas unCriterioDeVentaDeGas;
	private static ProveedoraDeRIGs proveedorRIGs;
	private static List<Pozo> pozos;
	private JTextField txtDistanciaInicialParcela;
	private JTextField txtPresionInicialParcela;
	private JTextField txtDurezaParcela;
	private JTextField txtComposicionInicialPetroleo;
	private JTextField txtComposicionInicialAgua;
	private JTextField txtComposicionInicialGas;
	private JTextField txtVolumenInicial;
	private static EquipoDeIngenieria equipoDeIngenieria;
	private static ConstructoraDeTanqueDeGas constructoraTanqueGas;
	private static ConstructoraDeTanqueDeAgua constructoraTanqueAgua;
	private static ConstructoraDePlantaSeparadora constructoraDePlantas;
	private static AvanzadorDeDias avanzador;
	private JTextField txtConsumoRIG;
	private JTextField txtPotenciaRIG;
	private JTextField txtPrecioAlquilerRIG;
	private JTextField txtMinimaCantidadDiasRIG;
	private JComboBox comboCriterioPlantas;
	private JComboBox comboCriterioExtraccion;
	private JComboBox comboCriterioTanques;
	private JComboBox comboCriterioReinyeccion;
	private JComboBox comboCriterioCompraAgua;
	private JComboBox comboCriterioSeleccionParcelasRIGs;
	private JComboBox comboCriterioTerminacion;
	private JComboBox comboCriterioVentaGas;
	private JTextPane txtRIGsActuales;
	private JTextPane txtParcelasActuales;
	private JTextField txtXCriterioPlantas;
	private JTextField txtXCriterioTanques;
	private JTextField txtXCriterioReinyeccion;
	private JTextField txtYCriterioReinyeccion;
	private JTextField txtXCriterioTerminacion;
	private JTextField txtXCriterioCompraAgua;
	private JTextField txtYCriterioTanques;
	private JTextField txtYCriterioPlantas;
	private JComboBox comboCostoPlanta;
	private JComboBox comboTiempoPlanta;
	private JComboBox comboCostoTanqueGas;
	private JComboBox comboTiempoTanqueGas;
	private JComboBox comboCostoTanqueAgua;
	private JComboBox comboTiempoTanqueAgua;
	private JTextField txtFactorTiempoPlanta;
	private JTextField txtFactorCostoPlanta;
	private JTextField txtFactorTiempoTanqueGas;
	private JTextField txtFactorCostoTanqueGas;
	private JTextField txtFactorTiempoTanqueAgua;
	private JTextField txtFactorCostoTanqueAgua;
	private static Thread simulationThread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// init variables
		parcelas = new ArrayList<>();
		subequipos = new ArrayList<>();
		unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs = new HashMap<>();
		unaListaDePreciosDeAlquilerDeRIGs = new HashMap<>();
		tanquesDeAgua = new StockDeTanques();
		tanquesDeGas = new StockDeTanques();
		plantasSeparadoras = new StockDePlantasSeparadoras();
		destileria = new Destileria();
		
		startUIThread();
	}

	private static void runSimulation() {
		simulationThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					armarSubequiposYConstructoras();
					armarEquipoInge();
					armarAvanzador();

					avanzador.avanzarDiasHastaTerminacion(simulationThread);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				// reiniciar fields basicos
				subequipos = new ArrayList<>();
				tanquesDeAgua = new StockDeTanques();
				tanquesDeGas = new StockDeTanques();
				plantasSeparadoras = new StockDePlantasSeparadoras();
				destileria = new Destileria();
				pozos = new ArrayList<>();
			}
		});
		
		simulationThread.start();
	}
	
	private static void armarAvanzador() {
		avanzador = new AvanzadorDeDias(equipoDeIngenieria, 
				constructoraTanqueGas, constructoraTanqueAgua, constructoraDePlantas, proveedorRIGs);
	}

	private static void armarEquipoInge() {
		RegistradorTarea registradorDeTareas = new RegistradorTarea(valorCompraCombustible, valorCompraAgua,
				valorVentaGas, valorVentaPetroleo, plantasSeparadoras, tanquesDeAgua, tanquesDeGas, pozos, reservorio);
		equipoDeIngenieria = new EquipoDeIngenieria(subequipos, 
				registradorDeTareas,
				unCriterioTerminacion);
	}

	private static void armarSubequiposYConstructoras() throws Exception {
		planificadorTanques = new SubequipoDeCompraDeTanques(unCriterioDeConstruccionTanques, tanquesDeAgua, tanquesDeGas);
		
		constructoraTanqueGas = new ConstructoraDeTanqueDeGas(unaFormaDeCalcularCostoGas,
				unaFormaDeCalcularElTiempoGas, planificadorTanques);
		constructoraTanqueAgua = new ConstructoraDeTanqueDeAgua(unaFormaDeCalcularCostoAgua,
				unaFormaDeCalcularElTiempoAgua, planificadorTanques);

		planificadorTanques.cambiarConstructoraDeTanquesDeGas(constructoraTanqueGas);
		planificadorTanques.cambiarConstructoraDeTanquesDeAgua(constructoraTanqueAgua);
		
		planificadorPlanta = new SubequipoDeCompraDePlantas(plantasSeparadoras, tanquesDeGas, tanquesDeAgua, unCriterioDeConstruccionDePlantas, destileria);
		constructoraDePlantas = new ConstructoraDePlantaSeparadora(unaFormaDeCalcularCostoPlanta, unaFormaDeCalcularElTiempoPlanta, planificadorPlanta);
		planificadorPlanta.cambiarConstructoraDePlantas(constructoraDePlantas);

		proveedorRIGs = new ProveedoraDeRIGs(unaListaDePreciosDeAlquilerDeRIGs, unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs, valorCompraCombustible);
		
		pozos = new ArrayList<Pozo>();
		SubequipoDeExtraccionYReinyeccion unSubequipoDeExtraccion = new SubequipoDeExtraccionYReinyeccion(plantasSeparadoras, tanquesDeAgua, tanquesDeGas,
				pozos, unCriterioDeExtraccion, unCriterioDeCompraDeAgua, unCriterioDeReinyeccion, reservorio);
		subequipos.add(unSubequipoDeExtraccion);
		
		ArrayList<Parcela> copy_parcelas = new ArrayList<Parcela>();
		copy_parcelas.addAll(parcelas);
		
		SubequipoDeExcavacion unSubequipoDeExcavacion = new SubequipoDeExcavacion(proveedorRIGs,
				unCriterioDeSeleccionDeParcelasConRIGs,
				copy_parcelas,
				pozos,
				reservorio);
		subequipos.add(unSubequipoDeExcavacion);
		
		subequipos.add(planificadorPlanta);
		subequipos.add(planificadorTanques);
		
		subequipos.add(new SubequipoDeVentaDeGas(unCriterioDeVentaDeGas, tanquesDeGas));
		subequipos.add(new SubequipoDeVentaDePetroleo(destileria));
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private static void startUIThread() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmSimoil.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void parsearConfiguracionUI() {
		// parametros generales
		alpha1 = Float.valueOf(txtAlpha1.getText());
		alpha2 = Float.valueOf(txtAlpha2.getText());
		valorVentaGas = Float.valueOf(txtVentaGas.getText());
		valorVentaPetroleo = Float.valueOf(txtVentaPetroleo.getText());
		valorCompraAgua = Float.valueOf(txtCompraAgua.getText());
		valorCompraCombustible = Float.valueOf(txtCompraCombustible.getText());
		dilucionCritica = Float.valueOf(txtDilucionCritica.getText());		
		// reservorio
		volumenInicial = Float.valueOf(txtVolumenInicial.getText());
		reservorio = new Reservorio(
				new CompuestoGasAguaPetroleo(
						Float.valueOf(txtComposicionInicialGas.getText()),
						Float.valueOf(txtComposicionInicialAgua.getText()),
						Float.valueOf(txtComposicionInicialPetroleo.getText())),
				new VolumenEnMetrosCubicos(volumenInicial),
				new VolumenEnMetrosCubicos(volumenInicial));
		// criterios Equipo Inge
		switch ((String)comboCriterioPlantas.getSelectedItem()) {
			case "Una nueva Planta por dia de X litros":
					unCriterioDeConstruccionDePlantas = new CCPlantasUnaNuevaPorDia(
							new VolumenEnMetrosCubicos(Float.valueOf(txtXCriterioPlantas.getText())));
				break;
			case "Y Plantas de X litros":
				unCriterioDeConstruccionDePlantas = new CCPlantasCantidadFija(
						Integer.valueOf(txtYCriterioPlantas.getText()),
						new VolumenEnMetrosCubicos(Float.valueOf(txtXCriterioPlantas.getText())));
				break;
		}
		switch ((String)comboCriterioTanques.getSelectedItem()) {
			case "Un nuevo Tanque de Agua y Gas por dia de X litros":
				VolumenEnMetrosCubicos volumen = new VolumenEnMetrosCubicos(Float.valueOf(txtXCriterioTanques.getText()));
				unCriterioDeConstruccionTanques = new CCTanquesUnoNuevoPorDia(volumen);
				break;
			case "Y Tanques de Agua y Gas de X litros":
				unCriterioDeConstruccionTanques = new CCTanquesCantidadFija(
						Integer.valueOf(txtYCriterioTanques.getText()),
						new VolumenEnMetrosCubicos(Float.valueOf(txtXCriterioTanques.getText())));
				break;
			case "Y Tanques de Agua y Gas de X litros cuando no hay más lugar":
				unCriterioDeConstruccionTanques = new CCTanquesPorNecesidad(
						Integer.valueOf(txtYCriterioTanques.getText()),
						new VolumenEnMetrosCubicos(Float.valueOf(txtXCriterioTanques.getText())));
				break;
		}
		switch ((String)comboCriterioExtraccion.getSelectedItem()) {
			case "Saco lo mas que pueda":
				unCriterioDeExtraccion = new CriterioDeExtraccionSacoLoMasQuePueda(alpha1, alpha2);
				break;
			case "Saco de manera Random":
				unCriterioDeExtraccion = new CriterioDeExtraccionRandom(alpha1, alpha2);
				break;
		}
		switch ((String)comboCriterioReinyeccion.getSelectedItem()) {
			case "Reinyectar X litros de Agua cuando la presion sea menor a Y":
				VolumenEnMetrosCubicos volumen = new VolumenEnMetrosCubicos(Float.valueOf(txtXCriterioReinyeccion.getText()));
				PresionEnHectopascales presion = new PresionEnHectopascales(Float.valueOf(txtYCriterioReinyeccion.getText()));
				unCriterioDeReinyeccion = new CriterioDeReinyeccionCantidadDeLitrosFijosCuandoLaPresionEsMenor(presion, volumen);
				break;
			case "Reinyectar lo más que se pueda cuando la presion sea menor a Y":
				unCriterioDeReinyeccion = new CriterioDeReinyeccionLoMasQuePueda(
						new PresionEnHectopascales(Float.valueOf(txtYCriterioReinyeccion.getText())));
				break;
		}
		switch ((String)comboCriterioSeleccionParcelasRIGs.getSelectedItem()) {
			case "Priorizar Parcelas con mayor presion y RIGs con menor precio":
				unCriterioDeSeleccionDeParcelasConRIGs = new CSPRPriorizandoParcelasConMayorPresionYRIGsBaratos();
				break;
			case "Priorizar Parcelas con menor dureza y RIGs con menor precio":
				unCriterioDeSeleccionDeParcelasConRIGs = new CSPRPriorizandoParcelasConMenorDurezaYRIGsBaratos();
				break;
			case "Priorizar Parcelas con mayor presion y RIGs con mayor potencia":
				unCriterioDeSeleccionDeParcelasConRIGs = new CSPRPriorizandoParcelasConMayorPresionYRIGsPotentes();
				break;
			case "Priorizar Parcelas con menor dureza y RIGs con mayor potencia":
				unCriterioDeSeleccionDeParcelasConRIGs = new CSPRPriorizandoParcelasConMenorDurezaYRIGsPotentes();
				break;
		}
		switch ((String)comboCriterioTerminacion.getSelectedItem()) {
			case "Terminar despues de X dias":
				int dias = Integer.valueOf(txtXCriterioTerminacion.getText());
				unCriterioTerminacion = new CriterioDeTerminacionFijo(dias);
				break;
			case "Criterio de Terminacion Random":
				unCriterioTerminacion = new CriterioDeTerminacionRandom();
				break;
			case "Criterio de Terminación por Dilución Crítica":
				unCriterioTerminacion = new CTPorDilucionCritica(dilucionCritica, reservorio);
				break;
		}
		switch ((String)comboCriterioVentaGas.getSelectedItem()) {
			case "Nunca vendo":
				unCriterioDeVentaDeGas = new CVGasNuncaVendo(unPrecioDelMetroCubicoGas);
				break;
			case "Siempre vendo":
				unCriterioDeVentaDeGas = new CVGasSiempreVendo(unPrecioDelMetroCubicoGas);
				break;
		}
		switch ((String)comboCriterioCompraAgua.getSelectedItem()) {
			case "Comprar X litros por dia":
				VolumenEnMetrosCubicos volumen = new VolumenEnMetrosCubicos(Float.valueOf(txtXCriterioCompraAgua.getText()));
				unCriterioDeCompraDeAgua = new CriterioDeCompraDeAguaFijoPorDia(volumen);
				break;
			case "Mantener los Tanques siempre llenos hasta X porcentaje":
				unCriterioDeCompraDeAgua = new CriterioDeCompraDeAguaPorPorcentajeLleno(Integer.valueOf(txtXCriterioCompraAgua.getText()));
				break;
		}
		
		// criterios empresas constructoras
		switch ((String)comboCostoPlanta.getSelectedItem()) {
			case "Lineal al volumen multiplicado por un factor":
				unaFormaDeCalcularCostoPlanta = new FDCCLinealAlVolumen(Float.valueOf(txtFactorCostoPlanta.getText()));
				break;
			case "Cuadratico al volumen multiplicado por un factor":
				unaFormaDeCalcularCostoPlanta = new FDCCCuadraticoAlVolumen(Float.valueOf(txtFactorCostoPlanta.getText()));
				break;
		}
		switch ((String)comboTiempoPlanta.getSelectedItem()) {
			case "Lineal al volumen multiplicado por un factor":
				unaFormaDeCalcularElTiempoPlanta = new FDETLinealAlVolumen(Float.valueOf(txtFactorTiempoPlanta.getText()));
				break;
			case "Cuadratico al volumen multiplicado por un factor":
				unaFormaDeCalcularElTiempoPlanta = new FDETCuadraticoAlVolumen(Float.valueOf(txtFactorTiempoPlanta.getText()));
				break;
		}
		switch ((String)comboCostoTanqueGas.getSelectedItem()) {
			case "Lineal al volumen multiplicado por un factor":
				unaFormaDeCalcularCostoGas = new FDCCLinealAlVolumen(Float.valueOf(txtFactorCostoTanqueGas.getText()));
				break;
			case "Cuadratico al volumen multiplicado por un factor":
				unaFormaDeCalcularCostoGas = new FDCCCuadraticoAlVolumen(Float.valueOf(txtFactorCostoTanqueGas.getText()));
				break;
		}
		switch ((String)comboTiempoTanqueGas.getSelectedItem()) {
			case "Lineal al volumen multiplicado por un factor":
				unaFormaDeCalcularElTiempoGas = new FDETLinealAlVolumen(Float.valueOf(txtFactorTiempoTanqueGas.getText()));
				break;
			case "Cuadratico al volumen multiplicado por un factor":
				unaFormaDeCalcularElTiempoGas = new FDETCuadraticoAlVolumen(Float.valueOf(txtFactorTiempoTanqueGas.getText()));
				break;
		}
		switch ((String)comboCostoTanqueAgua.getSelectedItem()) {
			case "Lineal al volumen multiplicado por un factor":
				unaFormaDeCalcularCostoAgua = new FDCCLinealAlVolumen(Float.valueOf(txtFactorCostoTanqueAgua.getText()));
				break;
			case "Cuadratico al volumen multiplicado por un factor":
				unaFormaDeCalcularCostoAgua = new FDCCCuadraticoAlVolumen(Float.valueOf(txtFactorCostoTanqueAgua.getText()));
				break;
		}
		switch ((String)comboTiempoTanqueAgua.getSelectedItem()) {
			case "Lineal al volumen multiplicado por un factor":
				unaFormaDeCalcularElTiempoAgua = new FDETLinealAlVolumen(Float.valueOf(txtFactorTiempoTanqueAgua.getText()));
				break;
			case "Cuadratico al volumen multiplicado por un factor":
				unaFormaDeCalcularElTiempoAgua = new FDETCuadraticoAlVolumen(Float.valueOf(txtFactorTiempoTanqueAgua.getText()));
				break;
		}
	}

	private void agregarParcelaDesdeUI() {
		Double presionInicial = Double.valueOf(txtPresionInicialParcela.getText());
		Integer distanciaInicial = Integer.valueOf(txtDistanciaInicialParcela.getText());
		Float dureza = Float.valueOf(txtDurezaParcela.getText());
		
		Parcela parcela = new Parcela(
				new PresionEnHectopascales(presionInicial),
				distanciaInicial,
				dureza);
		parcelas.add(parcela);
		
		String str = txtParcelasActuales.getText();
		str += String.format("Parcela con presión inicial: %.2f, distancia inicial: %d, dureza: %.2f\n",
				presionInicial, distanciaInicial, dureza);
		txtParcelasActuales.setText(str);
	}
	
	private void agregarRIGDesdeUI() {
		Float potencia = Float.valueOf(txtPotenciaRIG.getText());
		Float consumo = Float.valueOf(txtConsumoRIG.getText());
		RIG rig = new RIG(potencia, consumo);
		
		Float precio = Float.valueOf(txtPrecioAlquilerRIG.getText());
		unaListaDePreciosDeAlquilerDeRIGs.put(rig, precio);
		
		Integer minimaCantDias = Integer.valueOf(txtMinimaCantidadDiasRIG.getText());
		unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs.put(rig, minimaCantDias);
		
		String str = txtRIGsActuales.getText();
		str += String.format("RIG con potencia: %.2f, consumo: %.2f, precio de alquiler: %.2f, cant. días mínimos de alquiler: %d\n",
				potencia, consumo, precio, minimaCantDias);
		txtRIGsActuales.setText(str);
	}
	
	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimoil = new JFrame();
		frmSimoil.setResizable(false);
		frmSimoil.setTitle("SimOil");
		frmSimoil.setBounds(100, 100, 1054, 658);
		frmSimoil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelParametros = new JPanel();
		tabbedPane.addTab("Generales", null, panelParametros, null);
		
		JLabel lblNewLabel = new JLabel("Alpha 1");
		lblNewLabel.setBounds(44, 39, 209, 15);
		
		txtAlpha1 = new JTextField();
		txtAlpha1.setText("0.1");
		txtAlpha1.setBounds(295, 37, 32, 19);
		txtAlpha1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Alpha 2");
		lblNewLabel_1.setBounds(345, 39, 240, 15);
		
		txtAlpha2 = new JTextField();
		txtAlpha2.setText("0.01");
		txtAlpha2.setBounds(582, 39, 39, 19);
		txtAlpha2.setColumns(10);
		
		JLabel lblValorVentaPetroleo = new JLabel("Valor venta Petroleo ($)");
		lblValorVentaPetroleo.setBounds(44, 63, 209, 15);
		
		txtVentaPetroleo = new JTextField();
		txtVentaPetroleo.setText("15");
		txtVentaPetroleo.setBounds(295, 61, 32, 19);
		txtVentaPetroleo.setHorizontalAlignment(SwingConstants.LEFT);
		txtVentaPetroleo.setColumns(10);
		
		JLabel lblValorVentaGas = new JLabel("Valor venta Gas ($)");
		lblValorVentaGas.setBounds(345, 63, 240, 15);
		
		txtVentaGas = new JTextField();
		txtVentaGas.setText("8");
		txtVentaGas.setBounds(582, 65, 39, 19);
		txtVentaGas.setHorizontalAlignment(SwingConstants.LEFT);
		txtVentaGas.setColumns(10);
		
		JLabel lblValorCompraAgua = new JLabel("Valor compra Agua ($)");
		lblValorCompraAgua.setBounds(44, 87, 209, 15);
		
		txtCompraAgua = new JTextField();
		txtCompraAgua.setText("5");
		txtCompraAgua.setBounds(295, 85, 32, 19);
		txtCompraAgua.setHorizontalAlignment(SwingConstants.LEFT);
		txtCompraAgua.setColumns(10);
		
		JLabel lblValorCompraCombustible = new JLabel("Valor compra Combustible ($)");
		lblValorCompraCombustible.setBounds(345, 87, 240, 15);
		
		txtCompraCombustible = new JTextField();
		txtCompraCombustible.setText("20");
		txtCompraCombustible.setBounds(582, 87, 39, 19);
		txtCompraCombustible.setHorizontalAlignment(SwingConstants.LEFT);
		txtCompraCombustible.setColumns(10);
		
		JLabel lblDilucinCrtica = new JLabel("Dilución Crítica (% petroleo)");
		lblDilucinCrtica.setBounds(44, 114, 209, 15);
		
		txtDilucionCritica = new JTextField();
		txtDilucionCritica.setText("20");
		txtDilucionCritica.setBounds(295, 114, 32, 19);
		txtDilucionCritica.setHorizontalAlignment(SwingConstants.LEFT);
		txtDilucionCritica.setColumns(10);
		
		panelParametros.setLayout(null);
		panelParametros.add(lblNewLabel);
		panelParametros.add(txtAlpha1);
		panelParametros.add(lblNewLabel_1);
		panelParametros.add(txtAlpha2);
		panelParametros.add(lblValorVentaPetroleo);
		panelParametros.add(txtVentaPetroleo);
		panelParametros.add(lblValorVentaGas);
		panelParametros.add(txtVentaGas);
		panelParametros.add(lblValorCompraAgua);
		panelParametros.add(txtCompraAgua);
		panelParametros.add(lblValorCompraCombustible);
		panelParametros.add(txtCompraCombustible);
		panelParametros.add(lblDilucinCrtica);
		panelParametros.add(txtDilucionCritica);
		
		JPanel panelEquipo = new JPanel();
		tabbedPane.addTab("Equipo de Ingenieria", null, panelEquipo, null);
		panelEquipo.setLayout(null);
		
		JLabel lblCriterioDeCompra = new JLabel("Criterio de Construcción de Plantas");
		lblCriterioDeCompra.setBounds(12, 29, 312, 15);
		panelEquipo.add(lblCriterioDeCompra);
		
		comboCriterioPlantas = new JComboBox();
		comboCriterioPlantas.setModel(new DefaultComboBoxModel(new String[] {"Y Plantas de X litros", "Una nueva Planta por dia de X litros"}));
		comboCriterioPlantas.setBounds(305, 24, 452, 24);
		panelEquipo.add(comboCriterioPlantas);
		
		JLabel lblCriterioDeConstruccin = new JLabel("Criterio de Construcción de Tanques");
		lblCriterioDeConstruccin.setBounds(12, 65, 312, 15);
		panelEquipo.add(lblCriterioDeConstruccin);
		
		comboCriterioTanques = new JComboBox();
		comboCriterioTanques.setModel(new DefaultComboBoxModel(new String[] {"Y Tanques de Agua y Gas de X litros cuando no hay más lugar", "Y Tanques de Agua y Gas de X litros", "Un nuevo Tanque de Agua y Gas por dia de X litros"}));
		comboCriterioTanques.setBounds(305, 60, 452, 24);
		panelEquipo.add(comboCriterioTanques);
		
		comboCriterioExtraccion = new JComboBox();
		comboCriterioExtraccion.setModel(new DefaultComboBoxModel(new String[] {"Saco lo mas que pueda", "Saco de manera Random"}));
		comboCriterioExtraccion.setBounds(305, 95, 452, 24);
		panelEquipo.add(comboCriterioExtraccion);
		
		JLabel lblCriterioDeExtraccin = new JLabel("Criterio de Extracción");
		lblCriterioDeExtraccin.setBounds(12, 100, 312, 15);
		panelEquipo.add(lblCriterioDeExtraccin);
		
		JLabel lblCriterioDeReinyeccin = new JLabel("Criterio de Reinyección");
		lblCriterioDeReinyeccin.setBounds(12, 136, 312, 15);
		panelEquipo.add(lblCriterioDeReinyeccin);
		
		comboCriterioReinyeccion = new JComboBox();
		comboCriterioReinyeccion.setModel(new DefaultComboBoxModel(new String[] {"Reinyectar lo más que se pueda cuando la presion sea menor a Y", "Reinyectar X litros de Agua cuando la presion sea menor a Y"}));
		comboCriterioReinyeccion.setBounds(305, 131, 452, 24);
		panelEquipo.add(comboCriterioReinyeccion);
		
		JLabel lblCriterioDeCompra_1 = new JLabel("Criterio de Compra de Agua");
		lblCriterioDeCompra_1.setBounds(12, 170, 312, 15);
		panelEquipo.add(lblCriterioDeCompra_1);
		
		comboCriterioCompraAgua = new JComboBox();
		comboCriterioCompraAgua.setModel(new DefaultComboBoxModel(new String[] {"Comprar X litros por dia", "Mantener los Tanques siempre llenos hasta X porcentaje"}));
		comboCriterioCompraAgua.setBounds(305, 165, 452, 24);
		panelEquipo.add(comboCriterioCompraAgua);
		
		JLabel lblCriterioDeSeleccin = new JLabel("Criterio de Selección de Parcelas con RIGs");
		lblCriterioDeSeleccin.setBounds(12, 206, 312, 15);
		panelEquipo.add(lblCriterioDeSeleccin);
		
		comboCriterioSeleccionParcelasRIGs = new JComboBox();
		comboCriterioSeleccionParcelasRIGs.setModel(new DefaultComboBoxModel(new String[] {
				"Priorizar Parcelas con mayor presion y RIGs con menor precio",
				"Priorizar Parcelas con menor dureza y RIGs con menor precio",
				"Priorizar Parcelas con mayor presion y RIGs con mayor potencia",
				"Priorizar Parcelas con menor dureza y RIGs con mayor potencia"}));
		comboCriterioSeleccionParcelasRIGs.setBounds(305, 201, 452, 24);
		panelEquipo.add(comboCriterioSeleccionParcelasRIGs);
		
		JLabel lblCriterioDeTerminacin = new JLabel("Criterio de Terminación");
		lblCriterioDeTerminacin.setBounds(12, 242, 312, 15);
		panelEquipo.add(lblCriterioDeTerminacin);
		
		comboCriterioTerminacion = new JComboBox();
		comboCriterioTerminacion.setModel(new DefaultComboBoxModel(new String[] {"Terminar despues de X dias", "Criterio de Terminación por Dilución Crítica", "Criterio de Terminacion Random"}));
		comboCriterioTerminacion.setBounds(305, 237, 452, 24);
		panelEquipo.add(comboCriterioTerminacion);
		
		JLabel lblCriterioDeVenta = new JLabel("Criterio de Venta de Gas");
		lblCriterioDeVenta.setBounds(12, 278, 312, 15);
		panelEquipo.add(lblCriterioDeVenta);
		
		comboCriterioVentaGas = new JComboBox();
		comboCriterioVentaGas.setModel(new DefaultComboBoxModel(new String[] {"Nunca vendo", "Siempre vendo"}));
		comboCriterioVentaGas.setBounds(305, 273, 452, 24);
		panelEquipo.add(comboCriterioVentaGas);
		
		txtXCriterioPlantas = new JTextField();
		txtXCriterioPlantas.setText("30");
		txtXCriterioPlantas.setColumns(10);
		txtXCriterioPlantas.setBounds(808, 27, 40, 19);
		panelEquipo.add(txtXCriterioPlantas);
		
		JLabel lblX = new JLabel("X =");
		lblX.setBounds(775, 29, 51, 15);
		panelEquipo.add(lblX);
		
		JLabel label = new JLabel("X =");
		label.setBounds(775, 65, 51, 15);
		panelEquipo.add(label);
		
		txtXCriterioTanques = new JTextField();
		txtXCriterioTanques.setText("100");
		txtXCriterioTanques.setColumns(10);
		txtXCriterioTanques.setBounds(808, 63, 40, 19);
		panelEquipo.add(txtXCriterioTanques);
		
		JLabel label_1 = new JLabel("X =");
		label_1.setBounds(775, 136, 51, 15);
		panelEquipo.add(label_1);
		
		txtXCriterioReinyeccion = new JTextField();
		txtXCriterioReinyeccion.setText("10");
		txtXCriterioReinyeccion.setColumns(10);
		txtXCriterioReinyeccion.setBounds(808, 134, 40, 19);
		panelEquipo.add(txtXCriterioReinyeccion);
		
		JLabel lblY = new JLabel("Y =");
		lblY.setBounds(872, 136, 51, 15);
		panelEquipo.add(lblY);
		
		txtYCriterioReinyeccion = new JTextField();
		txtYCriterioReinyeccion.setText("2000");
		txtYCriterioReinyeccion.setColumns(10);
		txtYCriterioReinyeccion.setBounds(905, 134, 80, 19);
		panelEquipo.add(txtYCriterioReinyeccion);
		
		JLabel label_2 = new JLabel("X =");
		label_2.setBounds(775, 242, 51, 15);
		panelEquipo.add(label_2);
		
		txtXCriterioTerminacion = new JTextField();
		txtXCriterioTerminacion.setText("200");
		txtXCriterioTerminacion.setColumns(10);
		txtXCriterioTerminacion.setBounds(808, 240, 40, 19);
		panelEquipo.add(txtXCriterioTerminacion);
		
		JLabel label_3 = new JLabel("X =");
		label_3.setBounds(775, 170, 51, 15);
		panelEquipo.add(label_3);
		
		txtXCriterioCompraAgua = new JTextField();
		txtXCriterioCompraAgua.setText("10");
		txtXCriterioCompraAgua.setColumns(10);
		txtXCriterioCompraAgua.setBounds(808, 168, 40, 19);
		panelEquipo.add(txtXCriterioCompraAgua);
		
		JLabel label_4 = new JLabel("Y =");
		label_4.setBounds(872, 65, 51, 15);
		panelEquipo.add(label_4);
		
		txtYCriterioTanques = new JTextField();
		txtYCriterioTanques.setText("20");
		txtYCriterioTanques.setColumns(10);
		txtYCriterioTanques.setBounds(905, 63, 80, 19);
		panelEquipo.add(txtYCriterioTanques);
		
		JLabel lablala2 = new JLabel("Y =");
		lablala2.setBounds(872, 29, 51, 15);
		panelEquipo.add(lablala2);
		
		txtYCriterioPlantas = new JTextField();
		txtYCriterioPlantas.setText("10");
		txtYCriterioPlantas.setColumns(10);
		txtYCriterioPlantas.setBounds(905, 27, 80, 19);
		panelEquipo.add(txtYCriterioPlantas);
		
		JPanel panelReservorio = new JPanel();
		panelReservorio.setLayout(null);
		panelReservorio.setToolTipText("");
		tabbedPane.addTab("Reservorio", null, panelReservorio, null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Agregar Parcela", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(408, 12, 368, 147);
		panelReservorio.add(panel);
		panel.setLayout(null);
		
		txtDistanciaInicialParcela = new JTextField();
		txtDistanciaInicialParcela.setText("30");
		txtDistanciaInicialParcela.setColumns(10);
		txtDistanciaInicialParcela.setBounds(298, 53, 50, 19);
		panel.add(txtDistanciaInicialParcela);
		
		JLabel lblDistanciaInicialAl = new JLabel("Distancia Inicial al Reservorio (m)");
		lblDistanciaInicialAl.setBounds(12, 53, 244, 15);
		panel.add(lblDistanciaInicialAl);
		
		txtPresionInicialParcela = new JTextField();
		txtPresionInicialParcela.setText("3000");
		txtPresionInicialParcela.setColumns(10);
		txtPresionInicialParcela.setBounds(298, 29, 50, 19);
		panel.add(txtPresionInicialParcela);
		
		JLabel lblPresinInicial = new JLabel("Presión Inicial (PSIA)");
		lblPresinInicial.setBounds(12, 29, 202, 15);
		panel.add(lblPresinInicial);
		
		JLabel lblPorcentajeDurezaTerreno = new JLabel("Porcentaje Dureza Terreno (%)");
		lblPorcentajeDurezaTerreno.setBounds(12, 76, 244, 15);
		panel.add(lblPorcentajeDurezaTerreno);
		
		txtDurezaParcela = new JTextField();
		txtDurezaParcela.setText("20");
		txtDurezaParcela.setColumns(10);
		txtDurezaParcela.setBounds(298, 76, 50, 19);
		panel.add(txtDurezaParcela);
		
		JButton btnAgregarParcela = new JButton("Agregar");
		btnAgregarParcela.setBounds(224, 103, 114, 25);
		btnAgregarParcela.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarParcelaDesdeUI();
			}
		});
		panel.add(btnAgregarParcela);
		
		JButton btnQuitarTodas = new JButton("Quitar todas");
		btnQuitarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parcelas.clear();
				txtParcelasActuales.setText("");
			}
		});
		btnQuitarTodas.setBounds(34, 103, 159, 25);
		panel.add(btnQuitarTodas);
		
		JLabel label_46 = new JLabel("Composicion inicial Petroleo (%)");
		label_46.setBounds(12, 63, 235, 15);
		panelReservorio.add(label_46);
		
		txtComposicionInicialPetroleo = new JTextField();
		txtComposicionInicialPetroleo.setText("50");
		txtComposicionInicialPetroleo.setHorizontalAlignment(SwingConstants.LEFT);
		txtComposicionInicialPetroleo.setColumns(10);
		txtComposicionInicialPetroleo.setBounds(265, 63, 75, 19);
		panelReservorio.add(txtComposicionInicialPetroleo);
		
		JLabel label_47 = new JLabel("Composicion inicial Agua (%)");
		label_47.setBounds(12, 87, 235, 15);
		panelReservorio.add(label_47);
		
		txtComposicionInicialAgua = new JTextField();
		txtComposicionInicialAgua.setText("30");
		txtComposicionInicialAgua.setHorizontalAlignment(SwingConstants.LEFT);
		txtComposicionInicialAgua.setColumns(10);
		txtComposicionInicialAgua.setBounds(265, 87, 75, 19);
		panelReservorio.add(txtComposicionInicialAgua);
		
		JLabel label_48 = new JLabel("Composicion inicial Gas (%)");
		label_48.setBounds(12, 114, 235, 15);
		panelReservorio.add(label_48);
		
		txtComposicionInicialGas = new JTextField();
		txtComposicionInicialGas.setText("20");
		txtComposicionInicialGas.setHorizontalAlignment(SwingConstants.LEFT);
		txtComposicionInicialGas.setColumns(10);
		txtComposicionInicialGas.setBounds(265, 114, 75, 19);
		panelReservorio.add(txtComposicionInicialGas);
		
		JLabel label_49 = new JLabel("Volumen inicial (m^3)");
		label_49.setBounds(12, 36, 235, 15);
		panelReservorio.add(label_49);
		
		txtVolumenInicial = new JTextField();
		txtVolumenInicial.setText("10000000");
		txtVolumenInicial.setHorizontalAlignment(SwingConstants.LEFT);
		txtVolumenInicial.setColumns(10);
		txtVolumenInicial.setBounds(265, 36, 75, 19);
		panelReservorio.add(txtVolumenInicial);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Parcelas agregadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_3.setBounds(0, 171, 1049, 423);
		panelReservorio.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		txtParcelasActuales = new JTextPane();
		txtParcelasActuales.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(txtParcelasActuales,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_3.add(scrollPane);
		
		JPanel panelRIGs = new JPanel();
		panelRIGs.setToolTipText("");
		panelRIGs.setLayout(null);
		tabbedPane.addTab("RIGs", null, panelRIGs, null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "RIGs agregados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_4.setBounds(0, 235, 1049, 359);
		panel_4.setLayout(new CardLayout(0, 0));
		
		txtRIGsActuales = new JTextPane();
		txtRIGsActuales.setEditable(false);
		
		JScrollPane scrollPane2 = new JScrollPane(txtRIGsActuales,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel_4.add(scrollPane2, "name_83724605080734");
		
		panelRIGs.add(panel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("");
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Agregar RIG disponible", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_2.setBounds(17, 23, 418, 191);
		panelRIGs.add(panel_2);
		
		txtConsumoRIG = new JTextField();
		txtConsumoRIG.setText("30");
		txtConsumoRIG.setColumns(10);
		txtConsumoRIG.setBounds(254, 53, 124, 19);
		panel_2.add(txtConsumoRIG);
		
		JLabel lblConsumolitrosdia = new JLabel("Consumo (litros/dia)");
		lblConsumolitrosdia.setBounds(12, 53, 224, 15);
		panel_2.add(lblConsumolitrosdia);
		
		txtPotenciaRIG = new JTextField();
		txtPotenciaRIG.setText("15");
		txtPotenciaRIG.setColumns(10);
		txtPotenciaRIG.setBounds(254, 29, 124, 19);
		panel_2.add(txtPotenciaRIG);
		
		JLabel lblPotenciaDeExcavacin = new JLabel("Potencia de excavación (m/día)");
		lblPotenciaDeExcavacin.setBounds(12, 29, 211, 15);
		panel_2.add(lblPotenciaDeExcavacin);
		
		JLabel lblPrecioAlquilerda = new JLabel("Precio alquiler ($/día)");
		lblPrecioAlquilerda.setBounds(12, 76, 224, 15);
		panel_2.add(lblPrecioAlquilerda);
		
		txtPrecioAlquilerRIG = new JTextField();
		txtPrecioAlquilerRIG.setText("100");
		txtPrecioAlquilerRIG.setColumns(10);
		txtPrecioAlquilerRIG.setBounds(254, 76, 124, 19);
		panel_2.add(txtPrecioAlquilerRIG);
		
		JButton btnAgregarRIG = new JButton("Agregar");
		btnAgregarRIG.setBounds(254, 143, 114, 25);
		btnAgregarRIG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarRIGDesdeUI();
			}
		});
		panel_2.add(btnAgregarRIG);
		
		txtMinimaCantidadDiasRIG = new JTextField();
		txtMinimaCantidadDiasRIG.setText("60");
		txtMinimaCantidadDiasRIG.setColumns(10);
		txtMinimaCantidadDiasRIG.setBounds(254, 103, 124, 19);
		panel_2.add(txtMinimaCantidadDiasRIG);
		
		JLabel lblMnimaCantidadDas = new JLabel("Mínima cantidad días de alquiler");
		lblMnimaCantidadDas.setBounds(12, 103, 224, 15);
		panel_2.add(lblMnimaCantidadDas);
		
		JButton btnQuitarTodos = new JButton("Quitar todos");
		btnQuitarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				unaListaDeMinimaCantidadDeDiasDeAlquilerDeRIGs.clear();
				unaListaDePreciosDeAlquilerDeRIGs.clear();
				txtRIGsActuales.setText("");
			}
		});
		btnQuitarTodos.setBounds(47, 143, 159, 25);
		panel_2.add(btnQuitarTodos);
		
		JPanel panelPlantasTanques = new JPanel();
		tabbedPane.addTab("Plantas y Tanques", null, panelPlantasTanques, null);
		panelPlantasTanques.setToolTipText("");
		panelPlantasTanques.setLayout(null);
		
		JLabel label_5 = new JLabel("Forma de Estimar Costo Planta");
		label_5.setBounds(42, 70, 312, 15);
		panelPlantasTanques.add(label_5);
		
		comboCostoPlanta = new JComboBox();
		comboCostoPlanta.setModel(new DefaultComboBoxModel(new String[] {"Lineal al volumen multiplicado por un factor", "Cuadratico al volumen multiplicado por un factor"}));
		comboCostoPlanta.setBounds(344, 70, 331, 24);
		panelPlantasTanques.add(comboCostoPlanta);
		
		JLabel label_6 = new JLabel("Forma de Estimar Tiempo Planta");
		label_6.setBounds(42, 36, 312, 15);
		panelPlantasTanques.add(label_6);
		
		comboTiempoPlanta = new JComboBox();
		comboTiempoPlanta.setModel(new DefaultComboBoxModel(new String[] {"Lineal al volumen multiplicado por un factor", "Cuadratico al volumen multiplicado por un factor"}));
		comboTiempoPlanta.setBounds(344, 36, 331, 24);
		panelPlantasTanques.add(comboTiempoPlanta);
		
		JLabel label_7 = new JLabel("Forma de Estimar Costo Tanque Gas");
		label_7.setBounds(42, 139, 312, 15);
		panelPlantasTanques.add(label_7);
		
		comboCostoTanqueGas = new JComboBox();
		comboCostoTanqueGas.setModel(new DefaultComboBoxModel(new String[] {"Lineal al volumen multiplicado por un factor", "Cuadratico al volumen multiplicado por un factor"}));
		comboCostoTanqueGas.setBounds(344, 139, 331, 24);
		panelPlantasTanques.add(comboCostoTanqueGas);
		
		JLabel label_8 = new JLabel("Forma de Estimar Tiempo Tanque Gas");
		label_8.setBounds(42, 108, 312, 15);
		panelPlantasTanques.add(label_8);
		
		comboTiempoTanqueGas = new JComboBox();
		comboTiempoTanqueGas.setModel(new DefaultComboBoxModel(new String[] {"Lineal al volumen multiplicado por un factor", "Cuadratico al volumen multiplicado por un factor"}));
		comboTiempoTanqueGas.setBounds(344, 103, 331, 24);
		panelPlantasTanques.add(comboTiempoTanqueGas);
		
		JLabel label_9 = new JLabel("Forma de Estimar Costo Tanque Agua");
		label_9.setBounds(42, 218, 312, 15);
		panelPlantasTanques.add(label_9);
		
		comboCostoTanqueAgua = new JComboBox();
		comboCostoTanqueAgua.setModel(new DefaultComboBoxModel(new String[] {"Lineal al volumen multiplicado por un factor", "Cuadratico al volumen multiplicado por un factor"}));
		comboCostoTanqueAgua.setBounds(344, 213, 331, 24);
		panelPlantasTanques.add(comboCostoTanqueAgua);
		
		JLabel label_10 = new JLabel("Forma de Estimar Tiempo Tanque Agua");
		label_10.setBounds(42, 182, 312, 15);
		panelPlantasTanques.add(label_10);
		
		comboTiempoTanqueAgua = new JComboBox();
		comboTiempoTanqueAgua.setModel(new DefaultComboBoxModel(new String[] {"Lineal al volumen multiplicado por un factor", "Cuadratico al volumen multiplicado por un factor"}));
		comboTiempoTanqueAgua.setBounds(344, 177, 331, 24);
		panelPlantasTanques.add(comboTiempoTanqueAgua);
		
		txtFactorTiempoPlanta = new JTextField();
		txtFactorTiempoPlanta.setText("0.5");
		txtFactorTiempoPlanta.setColumns(10);
		txtFactorTiempoPlanta.setBounds(793, 39, 40, 19);
		panelPlantasTanques.add(txtFactorTiempoPlanta);
		
		JLabel lblFactor = new JLabel("Factor =");
		lblFactor.setBounds(725, 41, 83, 15);
		panelPlantasTanques.add(lblFactor);
		
		txtFactorCostoPlanta = new JTextField();
		txtFactorCostoPlanta.setText("0.5");
		txtFactorCostoPlanta.setColumns(10);
		txtFactorCostoPlanta.setBounds(793, 73, 40, 19);
		panelPlantasTanques.add(txtFactorCostoPlanta);
		
		JLabel label_11 = new JLabel("Factor =");
		label_11.setBounds(725, 75, 83, 15);
		panelPlantasTanques.add(label_11);
		
		txtFactorTiempoTanqueGas = new JTextField();
		txtFactorTiempoTanqueGas.setText("0.5");
		txtFactorTiempoTanqueGas.setColumns(10);
		txtFactorTiempoTanqueGas.setBounds(793, 106, 40, 19);
		panelPlantasTanques.add(txtFactorTiempoTanqueGas);
		
		JLabel label_12 = new JLabel("Factor =");
		label_12.setBounds(725, 108, 83, 15);
		panelPlantasTanques.add(label_12);
		
		txtFactorCostoTanqueGas = new JTextField();
		txtFactorCostoTanqueGas.setText("0.5");
		txtFactorCostoTanqueGas.setColumns(10);
		txtFactorCostoTanqueGas.setBounds(793, 142, 40, 19);
		panelPlantasTanques.add(txtFactorCostoTanqueGas);
		
		JLabel label_13 = new JLabel("Factor =");
		label_13.setBounds(725, 144, 83, 15);
		panelPlantasTanques.add(label_13);
		
		txtFactorTiempoTanqueAgua = new JTextField();
		txtFactorTiempoTanqueAgua.setText("0.5");
		txtFactorTiempoTanqueAgua.setColumns(10);
		txtFactorTiempoTanqueAgua.setBounds(793, 180, 40, 19);
		panelPlantasTanques.add(txtFactorTiempoTanqueAgua);
		
		JLabel label_14 = new JLabel("Factor =");
		label_14.setBounds(725, 182, 83, 15);
		panelPlantasTanques.add(label_14);
		
		txtFactorCostoTanqueAgua = new JTextField();
		txtFactorCostoTanqueAgua.setText("0.5");
		txtFactorCostoTanqueAgua.setColumns(10);
		txtFactorCostoTanqueAgua.setBounds(793, 216, 40, 19);
		panelPlantasTanques.add(txtFactorCostoTanqueAgua);
		
		JLabel label_15 = new JLabel("Factor =");
		label_15.setBounds(725, 218, 83, 15);
		panelPlantasTanques.add(label_15);
		
		JPanel panelSimulacion = new JPanel();
		tabbedPane.addTab("Simulación", null, panelSimulacion, null);
		
		FindTextPane panel_1 = new FindTextPane();
		panel_1.setBorder(new TitledBorder(null, "Log de la Simulaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 49, 1049, 545);
		panelSimulacion.add(panel_1);
		
		panel_1.textArea.setEditable(false);

		// add message console to redirect STD OUT to txtLogSimulacion
		MessageConsole messageConsole = new MessageConsole(panel_1.textArea);
		messageConsole.redirectOut();
		messageConsole.redirectErr(Color.RED, null);
		//messageConsole.setMessageLines(100);
		
		JButton btnEmpezarSimulacion = new JButton("Empezar Simulación");
		btnEmpezarSimulacion.setBounds(309, 12, 172, 25);
		btnEmpezarSimulacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.textArea.setText("");
				parsearConfiguracionUI();
				runSimulation();
			}
		});
		
		panelSimulacion.setLayout(null);
		panelSimulacion.add(btnEmpezarSimulacion);
		
		JButton button = new JButton("Parar Simulación");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (simulationThread != null && simulationThread.isAlive()) {
					simulationThread.interrupt();
				}
			}
		});
		button.setBounds(509, 12, 172, 25);
		panelSimulacion.add(button);
		
		frmSimoil.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
}

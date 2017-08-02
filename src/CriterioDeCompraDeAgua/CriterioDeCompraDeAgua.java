package CriterioDeCompraDeAgua;


import Simulador.StockDeTanques;
import Tareas.CompraDeAgua;

public abstract class CriterioDeCompraDeAgua {
	
	public abstract CompraDeAgua planearCompraDeAgua(StockDeTanques tanquesDeAgua);
}

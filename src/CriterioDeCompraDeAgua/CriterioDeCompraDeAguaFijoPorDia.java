package CriterioDeCompraDeAgua;


import Simulador.StockDeTanques;
import Simulador.Tanque;
import Tareas.CompraDeAgua;
import Unidades.VolumenEnMetrosCubicos;

public class CriterioDeCompraDeAguaFijoPorDia extends CriterioDeCompraDeAgua {

	private VolumenEnMetrosCubicos litrosPorDia;
	
	public CriterioDeCompraDeAguaFijoPorDia(VolumenEnMetrosCubicos litrosPorDia) {
		this.litrosPorDia = litrosPorDia;
	}

	public CompraDeAgua planearCompraDeAgua(StockDeTanques tanquesDeAgua) {
		float volumenALLenar = 0f;
		for(Tanque t : tanquesDeAgua.dameTanques()) {
			float volRestante = t.dameVolumenMaximo().dameVolumen() - t.dameVolumenOcupado().dameVolumen();
			volumenALLenar += volRestante;
		}
		float min = Math.min(volumenALLenar, this.litrosPorDia.dameVolumen());
		if (min <= 0.0001f)
			return null;
		return new CompraDeAgua(new VolumenEnMetrosCubicos(min));
	}

}

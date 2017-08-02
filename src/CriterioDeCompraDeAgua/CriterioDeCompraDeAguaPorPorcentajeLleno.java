package CriterioDeCompraDeAgua;


import Simulador.StockDeTanques;
import Simulador.Tanque;
import Tareas.CompraDeAgua;
import Unidades.VolumenEnMetrosCubicos;

public class CriterioDeCompraDeAguaPorPorcentajeLleno extends CriterioDeCompraDeAgua {

	private int porcentajeLlenoSobre100;
	
	public CriterioDeCompraDeAguaPorPorcentajeLleno(int porcentajeLlenoSobre100) {
		this.porcentajeLlenoSobre100 = porcentajeLlenoSobre100;
	}
	
	public CompraDeAgua planearCompraDeAgua(StockDeTanques tanquesDeAgua) {
		float volumenOcupado = 0f;
		float volumenMaximo = 0f;
		for(Tanque t : tanquesDeAgua.dameTanques()) {
			volumenOcupado += t.dameVolumenOcupado().dameVolumen();
			volumenMaximo += t.dameVolumenMaximo().dameVolumen();
		}
		float volumenALLenar = volumenMaximo * porcentajeLlenoSobre100/100;
		if (volumenALLenar > volumenOcupado) {
			return new CompraDeAgua(new VolumenEnMetrosCubicos(volumenALLenar-volumenOcupado));
		} else {
			return null;
		}
	}

}

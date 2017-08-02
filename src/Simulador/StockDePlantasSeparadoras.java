package Simulador;

import java.util.ArrayList;
import java.util.List;

public class StockDePlantasSeparadoras {
	private List<PlantaSeparadora> plantas;
	
	public StockDePlantasSeparadoras() {
		plantas = new ArrayList<PlantaSeparadora>();
	}
	
	public void agregarPlantaSeparadora(PlantaSeparadora unaPlantaSeparadora){
		plantas.add( unaPlantaSeparadora );
	}
	
	/**
	 * Copia por referencia
	 * @return List<PlantaSeparadora>
	 */
	public List<PlantaSeparadora> damePlantasSeparadoras(){
		return this.plantas;
	}
}

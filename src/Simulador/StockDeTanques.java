package Simulador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Unidades.VolumenEnMetrosCubicos;

public class StockDeTanques {
	private List<Tanque> tanques;
	
	public StockDeTanques() {
		tanques = new ArrayList<Tanque>();
	}
	
	public void agregarTanque(Tanque unTanque){
		tanques.add( unTanque );
	}
	
	/**
	 * Copia por referencia
	 * @return List<Tanque>
	 */
	public List<Tanque> dameTanques(){
		return this.tanques;
	}
	
	/**
	 * Por Copia
	 * @return
	 */
	public VolumenEnMetrosCubicos totalVolumen() {
		VolumenEnMetrosCubicos volTotal = new VolumenEnMetrosCubicos(0f);
		for (Tanque tanque : tanques) {
			volTotal.sumar(tanque.dameVolumenOcupado());
		}
		return volTotal;
	}

	public Map<Tanque, VolumenEnMetrosCubicos> extraerDeTanquesHasta(VolumenEnMetrosCubicos volAExtraer) {
		Map<Tanque, VolumenEnMetrosCubicos> diccTanqueVolAExtraer = new HashMap<Tanque, VolumenEnMetrosCubicos>();
		
		for (Tanque tanque : tanques) {
			if( tanque.dameVolumenOcupado().dameVolumen() > 0  && volAExtraer.dameVolumen() > 0){
				VolumenEnMetrosCubicos min = new VolumenEnMetrosCubicos(
						Math.min(tanque.dameVolumenOcupado().dameVolumen(), volAExtraer.dameVolumen()) );
				diccTanqueVolAExtraer.put(tanque, min);
				volAExtraer.restar(min);
			}
		}
		return diccTanqueVolAExtraer;
	}
}

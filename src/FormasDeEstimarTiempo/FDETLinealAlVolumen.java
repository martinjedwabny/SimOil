package FormasDeEstimarTiempo;
import Unidades.VolumenEnMetrosCubicos;

public class FDETLinealAlVolumen implements IFormaDeEstimarElTiempo{
	
	private float factor;

	public FDETLinealAlVolumen(float factor) {
		this.factor = factor;		
	}
	
	@Override
	public int estimarTiempoEnDias(VolumenEnMetrosCubicos unVolumenDeObra) {
		double ceil = Math.ceil(unVolumenDeObra.dameVolumen() * factor);
		return (int)ceil;
	}

}

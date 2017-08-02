package FormaDeCalcularElCosto;

import Unidades.VolumenEnMetrosCubicos;

public class FDCCLinealAlVolumen implements IFormaDeCalcularElCosto{
	
	private float factor;

	public FDCCLinealAlVolumen(float factor) {
		this.factor = factor;
	}
	
	@Override
	public float calcularCosto(VolumenEnMetrosCubicos unVolumenDeObra) {
		return unVolumenDeObra.dameVolumen() * factor;
	}

}

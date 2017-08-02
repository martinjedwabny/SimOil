package FormaDeCalcularElCosto;

import Unidades.VolumenEnMetrosCubicos;

public class FDCCCuadraticoAlVolumen implements IFormaDeCalcularElCosto{
	
	private float factor;

	public FDCCCuadraticoAlVolumen(float factor) {
		this.factor = factor;
	}
	
	@Override
	public float calcularCosto(VolumenEnMetrosCubicos unVolumenDeObra) {
		Double valorDelVolumenAlCudrado = Math.pow(unVolumenDeObra.dameVolumen(), 2);
		return valorDelVolumenAlCudrado.floatValue() * factor;
	}

}

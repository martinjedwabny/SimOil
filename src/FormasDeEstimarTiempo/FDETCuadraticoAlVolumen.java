package FormasDeEstimarTiempo;
import Unidades.VolumenEnMetrosCubicos;

public class FDETCuadraticoAlVolumen implements IFormaDeEstimarElTiempo{

	private float factor;

	public FDETCuadraticoAlVolumen(float factor) {
		this.factor = factor;
	}
	
	@Override
	public int estimarTiempoEnDias(VolumenEnMetrosCubicos unVolumenDeObra) {
		double volumenAlCudrado = Math.pow(unVolumenDeObra.dameVolumen(), 2);
		double ceil = Math.ceil(volumenAlCudrado * factor);
		return (int)ceil;
	}
	
}

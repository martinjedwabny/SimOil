package CriterioDeTerminacion;

public class CriterioDeTerminacionFijo extends CriterioDeTerminacion {

	private int dias;
	
	public CriterioDeTerminacionFijo(int dias) {
		super();
		this.dias = dias;
	}

	@Override
	public Boolean termino() {
		dias--;
		return dias==0;
	}

}

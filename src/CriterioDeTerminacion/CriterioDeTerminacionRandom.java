package CriterioDeTerminacion;

import java.util.Random;

public class CriterioDeTerminacionRandom extends CriterioDeTerminacion {

	@Override
	public Boolean termino() {
		Random r = new Random(System.currentTimeMillis());
		int n = r.nextInt(30); 
		return n == 10;
	}
}

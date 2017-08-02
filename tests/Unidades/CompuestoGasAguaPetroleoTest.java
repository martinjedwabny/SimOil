package Unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import Unidades.CompuestoGasAguaPetroleo;

public class CompuestoGasAguaPetroleoTest {

	@Test
	public void testCompuestoGasAguaPetroleoFloatFloatFloat() {
		CompuestoGasAguaPetroleo compuesto = new CompuestoGasAguaPetroleo(20f, 30f, 50f);
		assertTrue(compuesto.damePorcentajeDeGasSobre100() == 20f);
		assertTrue(compuesto.damePorcentajeDeAguaSobre100() == 30f);
		assertTrue(compuesto.damePorcentajeDePetroleoSobre100() == 50f);
	}

	@Test
	public void testCompuestoGasAguaPetroleoCompuestoGasAguaPetroleo() {
		CompuestoGasAguaPetroleo compuesto = new CompuestoGasAguaPetroleo(20f, 30f, 50f);
		CompuestoGasAguaPetroleo otroCompuesto = new CompuestoGasAguaPetroleo(compuesto);
		assertTrue(otroCompuesto.damePorcentajeDeGasSobre100() == 20f);
		assertTrue(otroCompuesto.damePorcentajeDeAguaSobre100() == 30f);
		assertTrue(otroCompuesto.damePorcentajeDePetroleoSobre100() == 50f);
	}

}

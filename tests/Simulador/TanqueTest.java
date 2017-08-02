package Simulador;

import static org.junit.Assert.*;

import org.junit.Test;

import Unidades.VolumenEnMetrosCubicos;

public class TanqueTest {
	
	@Test
	public void testTanqueUnVolumenMaximo() {
		VolumenEnMetrosCubicos volumenTotal = new VolumenEnMetrosCubicos(3.0f);
		Tanque primerTanque = new Tanque(volumenTotal);
		assertTrue(primerTanque.dameVolumenMaximo().dameVolumen() == volumenTotal.dameVolumen());
		assertTrue(primerTanque.dameVolumenOcupado().dameVolumen() == 0f);
		//assertTrue(primerTanque.getVolumenOcupado().dameVolumen() == VolumenEnMetrosCubicos.volumenInicial);
	}

	@Test
	public void testTanqueUnTanque() {
		VolumenEnMetrosCubicos volumenTotal = new VolumenEnMetrosCubicos(3.0f);
		Tanque primerTanque = new Tanque(volumenTotal);
		Tanque segundoTanque = new Tanque(primerTanque);
		assertTrue(primerTanque.dameVolumenMaximo().dameVolumen() == segundoTanque.dameVolumenMaximo().dameVolumen());
		assertTrue(primerTanque.dameVolumenOcupado().dameVolumen() == segundoTanque.dameVolumenOcupado().dameVolumen());
	}

	@Test
	public void testAlmacenar() {
		VolumenEnMetrosCubicos volumenTotal = new VolumenEnMetrosCubicos(3.0f);
		Tanque primerTanque = new Tanque(volumenTotal);
		VolumenEnMetrosCubicos volumenAlmacenar = new VolumenEnMetrosCubicos(2.0f);
		primerTanque.almacenar(volumenAlmacenar);
		assertTrue(primerTanque.dameVolumenMaximo().dameVolumen() == volumenTotal.dameVolumen());
		assertTrue(primerTanque.dameVolumenOcupado().dameVolumen() == 2.0f);
	}

	@Test
	public void testExtraer() {
		VolumenEnMetrosCubicos volumenTotal = new VolumenEnMetrosCubicos(3.0f);
		Tanque primerTanque = new Tanque(volumenTotal);
		VolumenEnMetrosCubicos volumenAlmacenar = new VolumenEnMetrosCubicos(2.0f);
		VolumenEnMetrosCubicos volumenExtraer = new VolumenEnMetrosCubicos(1.0f);
		primerTanque.almacenar(volumenAlmacenar);
		primerTanque.extraer(volumenExtraer);
		assertTrue(primerTanque.dameVolumenMaximo().dameVolumen() == volumenTotal.dameVolumen());
		assertTrue(primerTanque.dameVolumenOcupado().dameVolumen() == 1.0f);
	}
}

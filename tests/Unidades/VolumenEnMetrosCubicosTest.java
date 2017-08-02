package Unidades;

import static org.junit.Assert.*;

import org.junit.Test;

import Unidades.VolumenEnMetrosCubicos;

public class VolumenEnMetrosCubicosTest {

	@Test
	public void testVolumenEnMetrosCubicosVolumenEnMetrosCubicos() {
		VolumenEnMetrosCubicos primerVolumen = new VolumenEnMetrosCubicos(3.2f);
		VolumenEnMetrosCubicos segundoVolumen = new VolumenEnMetrosCubicos(primerVolumen);
		assertTrue(segundoVolumen.dameVolumen() == 3.2f);
	}

	@Test
	public void testVolumenEnMetrosCubicosFloat() {
		VolumenEnMetrosCubicos volumen = new VolumenEnMetrosCubicos(3.2f);
		assertTrue(volumen.dameVolumen() == 3.2f);
	}

	@Test
	public void testSumar() {
		VolumenEnMetrosCubicos primerVolumen = new VolumenEnMetrosCubicos(3.2f);
		VolumenEnMetrosCubicos segundoVolumen = new VolumenEnMetrosCubicos(1f);
		segundoVolumen.sumar(primerVolumen);
		assertTrue(segundoVolumen.dameVolumen() == 4.2f);
	}

	@Test
	public void testRestar() {
		VolumenEnMetrosCubicos primerVolumen = new VolumenEnMetrosCubicos(3.2f);
		VolumenEnMetrosCubicos segundoVolumen = new VolumenEnMetrosCubicos(1f);
		primerVolumen.restar(segundoVolumen);
		assertTrue(primerVolumen.dameVolumen() == 2.2f);
	}
	
	@Test
	public void testRestarVolumenNoNegativo() {
		VolumenEnMetrosCubicos primerVolumen = new VolumenEnMetrosCubicos(3.2f);
		VolumenEnMetrosCubicos segundoVolumen = new VolumenEnMetrosCubicos(1f);
		segundoVolumen.restar(primerVolumen);
		assertTrue(segundoVolumen.dameVolumen() == 0.0f);
	}

	@Test
	public void testTomarUnPorcentajeSobre100() {
		VolumenEnMetrosCubicos volumen = new VolumenEnMetrosCubicos(2.0f);
		assertTrue(volumen.tomarUnPorcentajeSobre100(50f).dameVolumen() == 1f);
	}
}

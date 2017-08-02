package Subequipo;

import java.util.ArrayList;
import java.util.List;

import Simulador.Destileria;
import Tareas.Tarea;
import Tareas.VentaDePetroleo;
import Unidades.VolumenEnMetrosCubicos;

public class SubequipoDeVentaDePetroleo extends Subequipo {

	private Destileria destileria;
	private VolumenEnMetrosCubicos petroleoVendido;

	public SubequipoDeVentaDePetroleo(Destileria destileria) {
		this.destileria = destileria;
		this.petroleoVendido = new VolumenEnMetrosCubicos(0);
	}

	@Override
	public List<Tarea> realizarTareasDelDia() {
		List<Tarea> ventas = new ArrayList<>();
		VolumenEnMetrosCubicos volumenDestiladoHoy = destileria.volumenDestiladoHoy();
		if (volumenDestiladoHoy.dameVolumen() > 1f) { // al menos vendamos 1 litro..
			VentaDePetroleo ventaDePetroleo = new VentaDePetroleo(volumenDestiladoHoy);
			ventas.add(ventaDePetroleo);
			realizarVenta(ventaDePetroleo);	
		}
		return ventas;
	}

	private void realizarVenta(VentaDePetroleo ventaDePetroleo) {
		this.petroleoVendido.sumar(destileria.volumenDestiladoHoy());
		destileria.vaciar();
	}

	public VolumenEnMetrosCubicos damePetroleoVendido() {
		return petroleoVendido;
	}

}

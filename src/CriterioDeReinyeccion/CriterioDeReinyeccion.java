package CriterioDeReinyeccion;

import java.util.List;

import Simulador.Pozo;
import Simulador.StockDeTanques;
import Tareas.TareaReinyeccion;
import Unidades.VolumenEnMetrosCubicos;

public abstract class CriterioDeReinyeccion {

	public abstract List<TareaReinyeccion> planearReinyeccion(
			VolumenEnMetrosCubicos volumenGlobalExtraido,
			VolumenEnMetrosCubicos volumenGlobalReinyectado,
			List<Pozo> pozos, StockDeTanques tanquesDeAgua);
}

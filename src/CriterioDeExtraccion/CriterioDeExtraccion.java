package CriterioDeExtraccion;

import java.util.List;

import Simulador.Pozo;
import Simulador.Reservorio;
import Simulador.StockDePlantasSeparadoras;
import Tareas.TareaExtraccion;

public abstract class CriterioDeExtraccion {

	public abstract List<TareaExtraccion> planearExtraccion(
			List<Pozo> pozos, 
			StockDePlantasSeparadoras plantasSeparadoras,
			Reservorio reservorio);

}

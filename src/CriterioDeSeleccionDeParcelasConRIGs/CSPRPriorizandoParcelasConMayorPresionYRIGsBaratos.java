package CriterioDeSeleccionDeParcelasConRIGs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import CriterioDeSeleccionDeParcelasConRIGs.CriterioDeSeleccionDeParcelasConRIGs.AsignacionParcelaRIG;
import ProveedoraDeRigs.ProveedoraDeRIGs;
import ProveedoraDeRigs.RIG;
import Simulador.Parcela;

public class CSPRPriorizandoParcelasConMayorPresionYRIGsBaratos extends CriterioDeSeleccionDeParcelasConRIGs{

	@Override
	public List<AsignacionParcelaRIG> planearAsignaciones(List<Parcela> parcelas,
			ProveedoraDeRIGs proveedoraDeRIGs) {
		List<AsignacionParcelaRIG> asignaciones = new ArrayList<AsignacionParcelaRIG>();
		
		ordenarParcelasPorMayorPresion(parcelas);
		
		List<RIG> rigsNoAlquilados = proveedoraDeRIGs.RIGsDisponibles();
		ordenarRIGsPorMenorPrecio(rigsNoAlquilados);
		
		List<RIG> rigsAUsar = proveedoraDeRIGs.dameRIGsAlquilados();
		ordenarRIGsPorMenorPrecio(rigsAUsar);
		
		rigsAUsar.addAll(rigsNoAlquilados);
		
		for (Parcela p : parcelas) {
			if (!rigsAUsar.isEmpty() ) {				
				RIG r = rigsAUsar.get(0);
				asignaciones.add(new AsignacionParcelaRIG(p, r));
				rigsAUsar.remove(0);
			}
		}

		return asignaciones;
	}
}

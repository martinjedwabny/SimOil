package CriterioDeSeleccionDeParcelasConRIGs;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ProveedoraDeRigs.ProveedoraDeRIGs;
import ProveedoraDeRigs.RIG;
import Simulador.Parcela;

public abstract class CriterioDeSeleccionDeParcelasConRIGs {
	
	public class AsignacionParcelaRIG { 
	  private Parcela parcela;
	  private RIG rig;

	  public AsignacionParcelaRIG(Parcela parcela, RIG rig) {
		this.parcela = parcela;
		this.rig = rig;
	  }
	  
	  public Parcela dameParcela() {
		  return parcela;
	  }
	  
	  public RIG dameRIG() {
		  return rig;
	  }
	}
	
	protected void ordenarRIGsPorMenorPrecio(List<RIG> rigs) {
		Collections.sort(rigs, 
				new Comparator<RIG>() {
					@Override
					public int compare(RIG unRIG, RIG otroRIG) {
						Float diff = unRIG.damePotenciaDeExcavacionEnMetrosPorDia() - 
								otroRIG.damePotenciaDeExcavacionEnMetrosPorDia();
						return diff.intValue();
					}
			});
	}
	
	protected void ordenarRIGsPorMayorPotencia(List<RIG> rigs) {
		Collections.sort(rigs, 
				new Comparator<RIG>() {
					@Override
					public int compare(RIG unRIG, RIG otroRIG) {
						Float diff = otroRIG.damePotenciaDeExcavacionEnMetrosPorDia() -
								unRIG.damePotenciaDeExcavacionEnMetrosPorDia();
						return diff.intValue();
					}
			});
	}

	protected void ordenarParcelasPorMayorPresion(List<Parcela> parcelas) {
		Collections.sort(parcelas, 
				new Comparator<Parcela>() {
					@Override
					public int compare(Parcela unaParcela, Parcela otraParcela) {
						float diff = otraParcela.getPresionInicial().damePresion() -
								unaParcela.getPresionInicial().damePresion();
						return (int) diff;
					}
			});
	}

	protected void ordenarParcelasPorMenorDureza(List<Parcela> parcelas) {
		Collections.sort(parcelas, 
				new Comparator<Parcela>() {
					@Override
					public int compare(Parcela unaParcela, Parcela otraParcela) {
						Float diff = unaParcela.getResistenciaAlRIG() - otraParcela.getResistenciaAlRIG();
						return diff.intValue();
					}
			});
	}
	public abstract List<AsignacionParcelaRIG> planearAsignaciones(List<Parcela> parcelas, ProveedoraDeRIGs proveedoraDeRIGs);

}

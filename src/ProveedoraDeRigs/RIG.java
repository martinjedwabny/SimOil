package ProveedoraDeRigs;

public class RIG {
	
	final float potenciaDeExcavacionEnMetrosPorDia;
	//1 Litro = 1000 metros cubicos -> Pero igual no esta muy claro como modelar metros cubicos
	final float consumoDiarioEnLitros;
	
	public float dameConsumoDiarioEnLitros(){
		return this.consumoDiarioEnLitros;
	}
	
	public float damePotenciaDeExcavacionEnMetrosPorDia(){
		return this.potenciaDeExcavacionEnMetrosPorDia;
	}
	
	/**
	 * @param unPotenciaDeExcavacionEnMetrosPorDia
	 * @param unConsumoDiarioDeLitros
	 */
	public RIG(float unaPotenciaDeExcavacionEnMetrosPorDia, float unConsumoDiarioDeLitros){
		this.consumoDiarioEnLitros = unConsumoDiarioDeLitros;
		this.potenciaDeExcavacionEnMetrosPorDia = unaPotenciaDeExcavacionEnMetrosPorDia;
	}
}

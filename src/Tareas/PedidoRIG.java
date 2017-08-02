package Tareas;

import ProveedoraDeRigs.ProveedoraDeRIGs;
import ProveedoraDeRigs.RIG;
import Simulador.RegistradorTarea;

public class PedidoRIG extends Tarea {
	
	private RIG rig;
	private ProveedoraDeRIGs proveedoraDeRIGs;
	private int cantidadDeDias;
	
	public PedidoRIG(RIG unRig, ProveedoraDeRIGs unaProveedoraDeRIGs) {
		this.rig = unRig;
		this.proveedoraDeRIGs = unaProveedoraDeRIGs;
		this.cantidadDeDias = proveedoraDeRIGs.cantidadMinimaDeDíasParaAlquilar(rig);
	}
	
	public PedidoRIG(RIG unRig, ProveedoraDeRIGs unaProveedoraDeRIGs, int cantidadDeDias) {
		this.rig = unRig;
		this.proveedoraDeRIGs = unaProveedoraDeRIGs;
		this.cantidadDeDias = Math.min(cantidadDeDias,
				proveedoraDeRIGs.cantidadMinimaDeDíasParaAlquilar(rig));
	}

	public RIG rigAAlquilar() {
		return rig;
	}

	public int cantidadDeDiasDeAlquiler() {
		return cantidadDeDias;
	}

	public ProveedoraDeRIGs proveedoraDeRIGs() {
		return proveedoraDeRIGs;
	}

	@Override
	public void registrarseCon(RegistradorTarea registrador) {
		registrador.registrarPedidoRIG(this);
	}
}

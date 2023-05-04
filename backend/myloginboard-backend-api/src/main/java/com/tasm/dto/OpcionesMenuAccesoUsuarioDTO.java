package com.tasm.dto;

import java.io.Serializable;
import java.util.List;

public class OpcionesMenuAccesoUsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codigoOpcion;
	private String descripcionOpcion;
	private String vista;
	private String esFinal;
	private List<OpcionesMenuAccesoUsuarioDTO> opcionesMenu;
	
	public OpcionesMenuAccesoUsuarioDTO() {
		super();
	}
	
	public OpcionesMenuAccesoUsuarioDTO(Integer codigoOpcion, String descripcionOpcion, String vista, String esFinal,
			List<OpcionesMenuAccesoUsuarioDTO> opcionesMenu) {
		super();
		this.codigoOpcion = codigoOpcion;
		this.descripcionOpcion = descripcionOpcion;
		this.vista = vista;
		this.esFinal = esFinal;
		this.opcionesMenu = opcionesMenu;
	}

	public Integer getCodigoOpcion() {
		return codigoOpcion;
	}

	public void setCodigoOpcion(Integer codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}

	public String getDescripcionOpcion() {
		return descripcionOpcion;
	}

	public void setDescripcionOpcion(String descripcionOpcion) {
		this.descripcionOpcion = descripcionOpcion;
	}

	public String getVista() {
		return vista;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

	public String getEsFinal() {
		return esFinal;
	}

	public void setEsFinal(String esFinal) {
		this.esFinal = esFinal;
	}

	public List<OpcionesMenuAccesoUsuarioDTO> getOpcionesMenu() {
		return opcionesMenu;
	}

	public void setOpcionesMenu(List<OpcionesMenuAccesoUsuarioDTO> opcionesMenu) {
		this.opcionesMenu = opcionesMenu;
	}

}

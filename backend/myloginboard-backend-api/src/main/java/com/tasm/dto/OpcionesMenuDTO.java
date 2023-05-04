package com.tasm.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpcionesMenuDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoOpcion;
	private String descripcionOpcion;
        private String nombreCorto;
	private String detalleOpcion;
	private String vista;
	private Integer codigoOpcionPadre;
	private Integer nivel;
	private String esFinal;
	@JsonInclude(Include.NON_EMPTY)
	private String tieneAccesoRol;
		
	@JsonInclude(Include.NON_EMPTY)
	private List<OpcionesMenuDTO> opcionesMenu;
	
	@JsonInclude(Include.NON_NULL)
	private List<String> acciones;
	
	public OpcionesMenuDTO(Integer codigoOpcion, String descripcionOpcion,
			String vista, Integer codigoOpcionPadre, String esFinal, List<String> acciones) {
		super();
		this.codigoOpcion = codigoOpcion;
		this.descripcionOpcion = descripcionOpcion;
		this.vista = vista;
		this.codigoOpcionPadre = codigoOpcionPadre;
		this.esFinal = esFinal;
		this.acciones = acciones;
	}
}

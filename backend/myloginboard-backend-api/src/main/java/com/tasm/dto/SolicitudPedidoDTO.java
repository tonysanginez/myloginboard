package com.tasm.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudPedidoDTO {
	 	private Long codigoSolicitud;
		//private Short codigoEmpresa;
		private Long codigoCliente;
		private String estadoSolicitud;
		private String fechaSolicitud;
		private String estado;
		private String fechaIngreso;
		private String usuarioIngreso;
		private String fechaModificacion;
		private String usuarioModificacion;
		private String fechaCancelacion;
		private String usuarioCancelacion;
		private String fechaAprobacion;
		private String usuarioAprobacion;
		private String esRegistrado;
		private String fechaRegistroPedido;
		private String usuarioRegistro;
		private String direccion;
		private String telefono;
		private String observacion;
		private String fechaEntrega;
	    private Short codigoTarifario;
	    private Short codigoPlantilla;
}

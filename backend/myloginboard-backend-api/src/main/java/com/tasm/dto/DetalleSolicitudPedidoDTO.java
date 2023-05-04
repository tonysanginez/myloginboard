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
public class DetalleSolicitudPedidoDTO {
	 	private Long codigoSolicitud;
		//private Short codigoEmpresa;
		private Short lineaDetalle;
		private Long codigoProducto;
		private Short codigoTarifario;
		private String tieneTarifario;
		private Short cantidadPedido;
		private Double valorTarifario;
		private Double subtotal;
		private Double iva;
		private Double total;
		private String estado;
		private String fechaIngreso;
		private String usuarioIngreso;
		private String fechaModificacion;
		private String usuarioModificacion;
	    private Short codigoMedidaConversion;
}

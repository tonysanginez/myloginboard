package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputDetalleMovimientoInventarioDTO {
	private Short codigoEmpresa;
	private String codigoMovimientoInventario;
	private String codigoTipoDocumento;
	private String codigoProducto;
	private String cantidad;
	private String costoProducto;
	private String subtotalProducto;
	private String valorIva;
	private String valorDescuento;
	private String totalProducto;
	private String estado;
    private String usuarioIngreso;
}

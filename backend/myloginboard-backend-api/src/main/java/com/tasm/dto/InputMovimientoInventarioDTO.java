package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputMovimientoInventarioDTO {
	private Short codigoEmpresa;
	private String codigoTipoDocumento;
	private String descripcion;
	private String codigoInstitucion;
	private String referenciaComprobante;
	private String estado;
    private String usuarioIngreso;
}

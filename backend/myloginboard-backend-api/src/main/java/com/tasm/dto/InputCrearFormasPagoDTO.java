package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputCrearFormasPagoDTO {
    
	private Short codigoEmpresa;
	private String nemonico;
	private String nombreFormaPago;
	private String codigoSri;
	private String estado;
}

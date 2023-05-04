package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormasPagoDTO {
	private Long codigoFormasPago;
//  private Short codigoEmpresa;
	private String nemonico;
	private String nombreFormaPago;
	private String codigoSri;
	private String estado;
	private String fechaIngreso;
  
}

package com.tasm.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalesAccesoUsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal secuenciaUsuario;
	private Integer codigoEmpresa;
	private Integer codigoSucursal;
	private String nombreSucursal;
	
}

package com.tasm.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizacionesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoEmpresa;
	private String nombreEmpresa;
	private String estado;
	private String identificadorLoginUrl;
	
}

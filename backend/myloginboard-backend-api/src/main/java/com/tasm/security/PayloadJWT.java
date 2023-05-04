package com.tasm.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PayloadJWT {
	
	private Long secuenciaUsuario;
	private String codigoUsuario;
	private String email;
	private String nombreUsuario;
	
}
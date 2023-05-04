package com.tasm.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ValidacionNumeroTelefonicoDTO {

	private String formatoE164;
	private String codigoPais;
	private String tipo;
	private String region;
	private String esValido;
	private String numeroNacional;
	private String formatoNacional;
	private String formatoInternacional;
	private String ubicacion;
	private String zonaHoraria;
	private String formatoNacionalSoloNumeros;
	private String operadora;
	
}
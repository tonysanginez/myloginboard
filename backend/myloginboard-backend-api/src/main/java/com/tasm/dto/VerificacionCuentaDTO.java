package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VerificacionCuentaDTO {

	private boolean cuentaActiva;
	private String[] tipoAcceso;
	private boolean sugerirReconocimientoFacial;
	private String primerNombre;
	private String sexo;


}

package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputMarcaDTO {
	 	private Short codigoEmpresa;
	    private String nombreMarca;
            private String usuarioIngreso;
	    //private Integer codigoMarca;
	    private String estado;
}

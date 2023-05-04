package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputTipoPresentacionDTO {
	 	private Short codigoEmpresa;
	    private String nombreTipoPresentacion;
	    //private Integer codigoTipoPresentacion;
            private String usuarioIngreso;
	    private String estado;
}

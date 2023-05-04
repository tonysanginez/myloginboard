package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoIdentificacionDTO {
 	//private Short codigoEmpresa;
    private String nombreTipoIdentificacion;
    private Integer codigoTipoIdentificacion;
    private String nemonico;
    private String estado;
    private String fechaIngreso;
}

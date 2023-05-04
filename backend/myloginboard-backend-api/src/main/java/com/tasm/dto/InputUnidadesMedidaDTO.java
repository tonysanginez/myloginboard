package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputUnidadesMedidaDTO {
    
    private Short codigoEmpresa;
    private String nombreUnidadMedida;   
    private String abreviatura;   
    private String estado;  
    private String usuarioIngreso;
}

package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputTiposProductosDTO {
    
    private Short codigoEmpresa;
    private String nombreTipoProducto;   
    private String estado;  
    private String usuarioIngreso;
}

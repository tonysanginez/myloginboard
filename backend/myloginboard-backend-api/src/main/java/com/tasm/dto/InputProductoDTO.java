package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputProductoDTO {
    
    private Short codigoEmpresa;
    private String nombreProducto;
    private String descripcion;
    private Integer codigoTipoProducto;
    private Integer codigoTipoPresentacion;
    private Integer codigoMarca;
    private Integer codigoUnidadMedida;
    private String codigoBarra;
    private String aplicaIva;
    private String aplicaStock;
    private String estado;
    private String usuarioIngreso;
    
    
}

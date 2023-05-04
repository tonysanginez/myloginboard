package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    
    private Long codigoProducto;
//    private Short codigoEmpresa;
    private String nombreProducto;
    private String descripcion;
    private String tipoProducto;
    private Integer codigoTipoProducto;
    private String tipoPresentacion;
    private Integer codigoTipoPresentacion;
    private String marca;
    private Integer codigoMarca;
    private String unidadMedida;
    private Integer codigoUnidadMedida;
    private String codigoBarra;
    private String aplicaIva;
    private String aplicaStock;
    private String estado;
    private String fechaIngreso;
    
}

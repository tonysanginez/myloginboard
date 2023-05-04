package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiposProductosDTO {
    
    private Long codigoTipoProducto;
//  private Short codigoEmpresa;
  private String nombreTipoProducto;  
  private String estado;
  private String fechaIngreso;
  private String usuarioIngreso;
    
}

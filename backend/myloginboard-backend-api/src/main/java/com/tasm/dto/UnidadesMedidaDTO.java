package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadesMedidaDTO {
    
    private Long codigoUnidadMedida;
//  private Short codigoEmpresa;
  private String nombreUnidadMedida;  
  private String abreviatura;  
  private String estado;
  private String fechaIngreso;
  private String usuarioIngreso;
    
}

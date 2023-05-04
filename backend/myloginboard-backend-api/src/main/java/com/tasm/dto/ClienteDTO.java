package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    
    private Long codigoCliente;
//    private Short codigoEmpresa;
    private String numeroIdentificacion;
    private String razonSocial;
    private String nombreComercial;
    private String telefonoMovil;
    private String direccion;
    private String correoElectronico;
    private String nombreCliente;
    private String tipoIdentificacion;
    private Integer codigoTipoIdentificacion;
    private String estado;
    private String fechaIngreso;
    
}

package com.tasm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputClienteDTO {
	private Short codigoEmpresa;
    private String numeroIdentificacion;
    private String razonSocial;
    private String nombreComercial;
    private String telefonoMovil;
    private String direccion;
    private String correoElectronico;
    private String nombreCliente;
    private Short codigoTipoIdentificacion;
    private String estado;
    private String usuarioIngreso;
}

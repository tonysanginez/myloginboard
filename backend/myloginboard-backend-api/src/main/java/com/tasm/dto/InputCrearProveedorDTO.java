package com.tasm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputCrearProveedorDTO {
	
	//institucion
	//private Long codigoInstitucion;
	private Integer codigoEmpresa;
	private Integer codigoDetalleActividad;
	private Integer codigoTipoIdentificacion;
	private String numeroIdentificacion; 
	private String nombreInstitucion; 
	private String nombreComercial; 
	
	//datos nulos
	private Integer codigoPaisTelFijo;
	private Integer codigoPaisTelMovil;
	private Integer codigoPaisTelMovilContacto;
	private String direccion;
	private String paginaWeb;
	private String correoElectronico;
	private String telefonoMovil;
	private String telefonoMovilE164;
	private String telefonoFijo;
	private String telefonoFijoE164;
	private String nombrePersonaContacto;
	private String telefonoMovilContacto;
	private String telefonoMovilContactoE164;
	private String nombreRepresentanteLegal;
	private String nombrePresidente;
	private String nombreGerente;
	private String registroSanitario;
	private String tipoPersona;
	private String esContribuyenteEspecial;
	private String esGubernamental;
	private String esContribuyenterise;
	private String llevaContabilidad;
	private String esExtranjero;
	private String esProveedor;	
	//proveedores
	private Long codigoFormasPago;
	private String plazoOtorgado;
	private String direccionRetiro;
	private String nombreContactoRetiro;
	private String estado;


	
}

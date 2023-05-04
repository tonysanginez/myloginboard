package com.tasm.model.com;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "com_clientes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ComClientes implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @EqualsAndHashCode.Include
    @Column(name = "CODIGO_CLIENTE")
    private Long codigoCliente;
    
    @NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
    
    @NotNull
    @Column(name = "CODIGO_TIPO_IDENTIFICACION")
    private Short codigoTipoIdentificacion;
    
    @Size(max = 20)
    @Column(name = "NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;
    
    @Size(max = 30)
    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;
    
    @Size(max = 30)
    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;
    
    @Size(max = 30)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    
    @Size(max = 30)
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    
    @Size(max = 120)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    
    @Size(max = 120)
    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;
    
    @Size(max = 120)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    
    @Size(min = 1, max = 1)
    @Column(name = "SEXO")
    private String sexo;
    
    @Size(max = 300)
    @Column(name = "DIRECCION")
    private String direccion;
    
    @Size(max = 100)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    
    @Column(name = "CODIGO_PAIS_TEL_MOVIL")
    private Integer codigoPaisTelMovil;
    
    @Size(max=30)
    @Column (name="TELEFONO_MOVIL")
    private String telefonoMovil;
    
    @Size(max=30)
    @Column (name="TELEFONO_MOVIL_E164")
    private String telefonoMovilE164;
    
    @Column(name="CODIGO_PAIS_TEL_FIJO")
    private Integer codigoPaisTelFijo;
    
    @Size(max=30)
    @Column (name="TELEFONO_FIJO")
    private String telefonoFijo;
    
    @Size(max=30)
    @Column (name="TELEFONO_FIJO_E164")
    private String telefonoFijoE164;
    
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @NotNull
    @Size(max = 15)
    @Column(name = "USUARIO_INGRESO")
    private String usuarioIngreso;

    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Size(max = 15)
    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;
}

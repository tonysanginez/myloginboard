package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gen_instituciones")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GenInstituciones implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_INSTITUCION")
	private Long codigoInstitucion;
	
    @NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Integer codigoEmpresa;

    @NotNull
    @Column(name = "CODIGO_DETALLE_ACTIVIDAD")
    private Integer codigoDetalleActividad;

    @NotNull
    @Column(name = "CODIGO_TIPO_IDENTIFICACION")
    private Integer codigoTipoIdentificacion;

    @Size(max = 20)
    @Column(name = "NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;

    @Size(max = 100)
    @Column(name = "NOMBRE_INSTITUCION")
    private String nombreInstitucion;

    @Size(max = 100)
    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;
    
	
    @Size(max = 1000)
    @Column(name = "DIRECCION")
    private String direccion;
    
    @Size(max = 100)
    @Column(name = "PAGINA_WEB")
    private String paginaWeb;

    @Size(max = 100)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;

    @Size(max = 9)
    @Column(name = "CODIGO_PAIS_TEL_MOVIL")
    private Integer codigoPaisTelMovil;
	
    @Size(max = 30)
    @Column(name = "TELEFONO_MOVIL")
    private String telefonoMovil;

    @Size(max = 30)
    @Column(name = "TELEFONO_MOVIL_E164")
    private String telefonoMovilE164;

    @Size(max = 9)
    @Column(name = "CODIGO_PAIS_TEL_FIJO")
    private Integer codigoPaisTelFijo;

    @Size(max = 30)
    @Column(name = "TELEFONO_FIJO")
    private String telefonoFijo;

    @Size(max = 30)
    @Column(name = "TELEFONO_FIJO_E164")
    private String telefonoFijoE164;
    
    @Size(max = 100)
    @Column(name = "NOMBRE_PERSONA_CONTACTO")
    private String nombrePersonaContacto;
    
    @Size(max = 9)
    @Column(name = "CODIGO_PAIS_TEL_MOVIL_CONTACTO")
    private Integer codigoPaisTelMovilContacto;

    @Size(max = 30)
    @Column(name = "TELEFONO_MOVIL_CONTACTO")
    private String telefonoMovilContacto;

    @Size(max = 30)
    @Column(name = "TELEFONO_MOVIL_CONTACTO_E164")
    private String telefonoMovilContactoE164;
	
    @Size(max = 180)
    @Column(name = "NOMBRE_REPRESENTANTE_LEGAL")
    private String nombreRepresentanteLegal;

    @Size(max = 180)
    @Column(name = "NOMBRE_PRESIDENTE")
    private String nombrePresidente;

    @Size(max = 180)
    @Column(name = "NOMBRE_GERENTE")
    private String nombreGerente;

    @Size(max = 30)
    @Column(name = "REGISTRO_SANITARIO")
    private String registroSanitario;

    @Size(max = 1)
    @Column(name = "TIPO_PERSONA")
    private String tipoPersona;

    @Size(max = 1)
    @Column(name = "ES_CONTRIBUYENTE_ESPECIAL")
    private String esContribuyenteEspecial;

    @Size(max = 1)
    @Column(name = "ES_GUBERNAMENTAL")
    private String esGubernamental;

    @Size(max = 1)
    @Column(name = "ES_CONTRIBUYENTE_RISE")
    private String esContribuyenterise;

    @Size(max = 1)
    @Column(name = "LLEVA_CONTABILIDAD")
    private String llevaContabilidad;

    @Size(max = 1)
    @Column(name = "ES_EXTRANJERO")
    private String esExtranjero;

    @Size(max = 1)
    @Column(name = "ES_PROVEEDOR")
    private String esProveedor;

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

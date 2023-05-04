package com.tasm.model.gen;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_paises")
public class GenPaises implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @EqualsAndHashCode.Include
    @NotNull
    @Column(name = "CODIGO_PAIS")
    private Integer codigoPais;
    
    @Size(max = 100)
    @Column(name = "NOMBRE_PAIS")
    private String nombrePais;
    
    @Size(max = 100)
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    
    @Size(max = 30)
    @Column(name = "CONTINENTE")
    private String continente;
    
    @Size(max = 10)
	@Column(name = "CODIGO_ISO")
	private String codigoISO;
    
    @Size(max = 10)
    @Column(name = "CODIGO_TELEFONICO")
    private String codigoTelefonico;
    
    @Column(name = "CANTIDAD_DIGITOS_CELULAR")
    private Short cantidadDigitosCelular;
    
    @Column(name = "CANTIDAD_DIGITOS_FIJO")
    private Short cantidadDigitosFijo;
    
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    
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
package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "gen_tipos_identificacion")
public class GenTiposIdentificacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@EqualsAndHashCode.Include
    protected GenTiposIdentificacionCPK genTiposIdentificacionCPK;
	
	@NotNull
	@Size(max = 30)
	@Column(name = "NEMONICO")
	private String nemonico;
	
	@NotNull
	@Size(max = 60)
	@Column(name = "NOMBRE_TIPO_IDENTIFICACION")
	private String nombreTipoIdentificacion;
	
	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "TIPO_PERSONA")
	private String tipoPèrsona;
	
	@NotNull
	@Size(max = 10)
	@Column(name = "ABREVIATURA_IDENTIFICACION")
	private String abreviaturaIdentificacion;
	
	@Size(max = 3)
	@Column(name = "CODIGO_SRI")
	private String codigoSri;
	
	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "MOSTRAR_A_CLIENTE")
	private String mostrar_a_cliente;
	
	@Size(max = 60)
    @Column(name = "ALGORITMO_IDENTIFICACION")
    private String algoritmoIdentificacion;
	
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

	@NotNull
	@Column(name = "FECHA_MODIFICACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	@NotNull
	@Size(max = 15)
	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;
	
}
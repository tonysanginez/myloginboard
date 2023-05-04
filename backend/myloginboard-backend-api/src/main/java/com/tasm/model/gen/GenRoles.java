package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

/**
 * Entity implementation class for Entity: DafRolesSistema
 *
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor // (access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_roles")
public class GenRoles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Size(min = 1, max = 20)
	@EqualsAndHashCode.Include
	@Column(name = "SECUENCIA_ROL")
	private String secuenciaRol;

	@Size(max = 60)
	@Column(name = "NOMBRE")
	private String nombre;

	@Size(max = 2000)
	@Column(name = "DESCRIPCION")
	private String descripcion; 
	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "ESTADO")
	private String estado;

	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "ES_SISTEMA")
	private String esSistema;

	@Column(name = "FECHA_INGRESO"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@Size(max = 15)
	@Column(name = "USUARIO_INGRESO")
	private String usuarioIngreso;

	@Column(name = "FECHA_MODIFICACION"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	@Size(max = 15)
	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;
 
	 
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_EMPRESA", referencedColumnName = "CODIGO_EMPRESA", insertable = false, updatable = false)
	private GenEmpresas genEmpresas;

}
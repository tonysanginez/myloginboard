package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * Entity implementation class for Entity: DafOpcionesMenu
 *
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor // (access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_usuarios_x_sucursal")
public class GenUsuariosXSucursal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "SECUENCIA_USU_X_SUC")
	private Integer secuenciaUsuXSuc; 
 
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
 
 
 
 
	// usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SECUENCIA_USUARIO", referencedColumnName = "SECUENCIA_USUARIO")
	private GenUsuarios genUsuarios;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "CODIGO_EMPRESA", referencedColumnName = "CODIGO_EMPRESA"), @JoinColumn(name = "CODIGO_SUCURSAL", referencedColumnName = "CODIGO_SUCURSAL") })
	private GenSucursales genSucursales;

//	@OneToMany(mappedBy = "dafOpcionesMenu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//	private List<DafOpcionesMenu> dafOpcionesMenuList;

}

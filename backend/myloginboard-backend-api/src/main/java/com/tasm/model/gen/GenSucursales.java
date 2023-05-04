package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: DafSucursales
 *
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor // (access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_sucursal")
public class GenSucursales implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_EMPRESA", referencedColumnName = "CODIGO_EMPRESA", insertable = false, updatable = false)
	private GenEmpresas genEmpresas;

	@EmbeddedId
	@EqualsAndHashCode.Include
	protected GenSucursalesCPK genSucursalesCPK;

	@Size(max = 100)
	@Column(name = "NOMBRE_SUCURSAL")
	private String nombreSucursal;
 
	@Size(max = 1)
	@Column(name = "ESTADO")
	private String estado; 
	 
	@Size(max = 1)
	@Column(name = "ES_MATRIZ")
	private String esMatriz;

	@Size(max = 100)
	@Column(name = "DIRECCION")
	private String direccion;
 
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
 
  

//	@OneToMany(mappedBy = "dafOpcionesMenu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//	private List<DafOpcionesMenu> dafOpcionesMenuList;

}

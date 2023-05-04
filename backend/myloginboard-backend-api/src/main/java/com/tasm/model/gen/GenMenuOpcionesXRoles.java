package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * Entity implementation class for Entity: DafOpcionesMenu
 *
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor // (access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_menu_opciones_x_roles")
public class GenMenuOpcionesXRoles implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@EqualsAndHashCode.Include
	protected GenMenuOpcionesRolCPK genMenuOpcionesRolCPK; 
	
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

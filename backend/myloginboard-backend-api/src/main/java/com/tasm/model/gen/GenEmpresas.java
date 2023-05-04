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

/**
 * Entity implementation class for Entity: DafEmpresas
 *
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor//(access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_empresas")
public class GenEmpresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
    @EqualsAndHashCode.Include
	@Column(name = "CODIGO_EMPRESA")
	private Short codigoEmpresa;

	@Size(max = 100)
	@Column(name = "NOMBRE_EMPRESA")
	private String nombreEmpresa; 
	
	@Size(max = 1)
	@Column(name = "ESTADO")
	private String estado; 

	@Size(max = 30)
	@Column(name = "USUARIO_INGRESO")
	private String usuarioIngreso;

	@Column(name = "FECHA_INGRESO"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@Size(max = 30)
	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;

	@Column(name = "FECHA_MODIFICACION"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;
 

}

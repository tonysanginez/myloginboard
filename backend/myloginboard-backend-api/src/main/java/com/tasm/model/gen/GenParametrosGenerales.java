package com.tasm.model.gen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.tasm.util.ActivoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor // (access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_parametros_generales")
public class GenParametrosGenerales implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_EMPRESA", referencedColumnName = "CODIGO_EMPRESA", insertable = false, updatable = false)
	private GenEmpresas genEmpresas;

	@EmbeddedId
	@EqualsAndHashCode.Include
	protected GenParametrosGeneralesCPK genParametrosGeneralesCPK;
	 
	
	@Size(max = 3000)
	@Column(name = "DESCRIPCION")
	private String descripcion;
 
	@Size(max = 4000)
	@Column(name = "VALOR_VARCHAR")
	private String valorVarchar;
	 
	@Size(max = 20)
	@Column(name = "VALOR_NUMBER")
	private BigDecimal valorNumber;
 
	@Column(name = "VALOR_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date valorDate;
	
	@Size(max = 1)
	@Column(name = "ES_SISTEMA")
	private String esSistema;
	
	@Convert(converter = ActivoConverter.class)
	@Column(name = "ESTADO")
	private boolean estado;
 
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

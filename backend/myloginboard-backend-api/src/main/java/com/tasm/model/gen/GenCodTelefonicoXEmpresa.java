package com.tasm.model.gen;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "GEN_COD_TELEFONICO_X_EMPRESA")
public class GenCodTelefonicoXEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@EqualsAndHashCode.Include
	protected GenCodTelefonicoXEmpresaCPK dafCodTelefonicoXEmpresaCPK;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_PAIS", referencedColumnName = "CODIGO_PAIS", insertable = false, updatable = false)
	private GenPaises dafPaises;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_EMPRESA", referencedColumnName = "CODIGO_EMPRESA", insertable = false, updatable = false)
	private GenEmpresas dafEmpresas;
	
	@Size(max = 1)
	@Column(name = "ES_DEFAULT")
	private String esDefault;
	
	@Column(name = "ORDENAMIENTO")
	private Integer ordenamiento;
	
	@NotNull
	@Size(max = 1)
	@Column(name = "ESTADO")
	private String estado;
	
}
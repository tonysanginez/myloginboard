package com.tasm.model.gen;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class GenTiposIdentificacionCPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "CODIGO_TIPO_IDENTIFICACION")
	private Short codigoTipoIdentificacion;

	@NotNull
	@Column(name = "CODIGO_EMPRESA")
	private Short codigoEmpresa;
	
}
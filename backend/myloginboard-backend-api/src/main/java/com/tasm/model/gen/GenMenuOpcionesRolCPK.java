package com.tasm.model.gen;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor // (access = AccessLevel.PACKAGE)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GenMenuOpcionesRolCPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_MENU")
	private int codigoMenu;

	@NotNull
	@EqualsAndHashCode.Include
	@Size(min = 1, max = 20)
	@Column(name = "SECUENCIA_ROL")
	private String secuenciaRol;

}
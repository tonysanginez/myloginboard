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

/**
 * ID implementation class for DafSucursales Composite Primary Key
 *
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class GenSucursalesCPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "CODIGO_EMPRESA")
	private Short codigoEmpresa;

	@NotNull
	@Column(name = "CODIGO_SUCURSAL")
	private Integer codigoSucursal;

}

package com.tasm.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpcionesMenuRoleDTO {
	private Integer codigoOpcion;
	private List<String> acciones;
}

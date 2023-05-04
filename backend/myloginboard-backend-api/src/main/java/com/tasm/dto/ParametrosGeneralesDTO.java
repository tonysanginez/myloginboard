package com.tasm.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParametrosGeneralesDTO {

	private String descripcion; 
	private String valorVarchar;
	private BigDecimal valorNumber;
	private Date valorDate;


}

package com.tasm.bo;

import com.tasm.dto.ParametrosGeneralesDTO;
import com.tasm.exceptions.BOException;

public interface IGeneralesBO {
	
	   
	public ParametrosGeneralesDTO obtenerParametros(Integer codigoEmpresa, String nemonico) throws BOException;
}
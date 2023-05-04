package com.tasm.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasm.bo.IGeneralesBO;
import com.tasm.dao.GenParametrosGeneralesDAO;
import com.tasm.dto.ParametrosGeneralesDTO;
import com.tasm.exceptions.BOException;
import com.tasm.model.gen.GenParametrosGenerales;
import com.tasm.util.GenericUtil; 

@Service
public class GenaralesBOImpl implements IGeneralesBO {
	 
	  
	@Autowired
	private GenParametrosGeneralesDAO objGenParametrosGeneralesDAO;
	
	 
	@Override
	public ParametrosGeneralesDTO obtenerParametros(Integer codigoEmpresa, String nemonico) throws BOException {
	
		GenericUtil.validarCampoRequeridoBO(nemonico, "tasm.campos.nemonico");
		
		ParametrosGeneralesDTO objParametros = new ParametrosGeneralesDTO();
		GenParametrosGenerales objGenParametrosGenerales = objGenParametrosGeneralesDAO.obtenerParametro(codigoEmpresa, nemonico);

 		if (objGenParametrosGenerales == null) { 
			throw new BOException("tasm.info.noHayDatos");
		}
 		objParametros.setDescripcion(objGenParametrosGenerales.getDescripcion());
 		objParametros.setValorVarchar(objGenParametrosGenerales.getValorVarchar());
 		objParametros.setValorNumber(objGenParametrosGenerales.getValorNumber());
 		objParametros.setValorDate(objGenParametrosGenerales.getValorDate());
		 
 		return objParametros;
	}
	 
}

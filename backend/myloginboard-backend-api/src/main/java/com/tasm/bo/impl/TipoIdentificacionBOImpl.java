package com.tasm.bo.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasm.bo.ITipoIdentificacionBO;
import com.tasm.dao.GenTiposIdentificacionDAO;
import com.tasm.exceptions.BOException;
import com.tasm.util.GenericUtil;
@Service
public class TipoIdentificacionBOImpl implements ITipoIdentificacionBO{
	@Autowired
	private GenTiposIdentificacionDAO objGenTiposIdentificacionDAO;
	
	@Override
	public Map<String, Object> obtenerTiposIdentificacion(Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, Integer codigoTipoIdentificacion, String nemonico, String nombreTipoIdentificacion,
			String estado) throws BOException {
		 GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
	        GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
	        GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
	        
	        page = page <= 0 ? 1 : page;
	        perPage = perPage <= 0 ? 10 : perPage;
	        return objGenTiposIdentificacionDAO.obtenerTiposIdentificacion(codigoEmpresa, page, perPage, filtroGeneral, nombreTipoIdentificacion, codigoTipoIdentificacion, nemonico, estado);
	        
	}

}

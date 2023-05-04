package com.tasm.bo.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasm.bo.ISolicitudesBO;
import com.tasm.dao.MgiDetalleSolicitudPedidoDAO;
import com.tasm.dao.MgiSolicitudPedidoDAO;
import com.tasm.exceptions.BOException;
import com.tasm.util.GenericUtil;
@Service
public class SolicitudesBOImpl implements ISolicitudesBO{
	@Autowired
    private MgiSolicitudPedidoDAO objMgiSolicitudPedidoDAO;
	
	@Autowired
    private MgiDetalleSolicitudPedidoDAO objMgiDetalleSolicitudPedidoDAO;
	
	@Override
	public Map<String, Object> obtenerSolicitudPedido(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			Integer codigoCliente, String estadoSolicitud) throws BOException {
		 GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
	     GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
	     GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
	     page = page <= 0 ? 1 : page;
	        perPage = perPage <= 0 ? 10 : perPage;
	            
	    return objMgiSolicitudPedidoDAO.obtenerSolicitudPedido(codigoEmpresa, page, perPage,  filtroGeneral,
	              codigoCliente, estadoSolicitud);
	}

	@Override
	public Map<String, Object> obtenerDetalleSolicitudPedido( Integer page, Integer perPage,
			String filtroGeneral, Integer codigoSolicitud) throws BOException {
		 GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
	     GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
	     page = page <= 0 ? 1 : page;
	        perPage = perPage <= 0 ? 10 : perPage;
	            
	    return objMgiDetalleSolicitudPedidoDAO.obtenerDetalleSolicitudPedido( page, perPage,  filtroGeneral,
	              codigoSolicitud);
	}


}

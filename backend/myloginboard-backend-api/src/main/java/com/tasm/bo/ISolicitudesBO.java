package com.tasm.bo;

import java.util.Map;

import com.tasm.exceptions.BOException;

public interface ISolicitudesBO {
	public Map<String, Object> obtenerSolicitudPedido(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			 Integer codigoCliente,  String estadoSolicitud) throws BOException;
	
	public Map<String, Object> obtenerDetalleSolicitudPedido( Integer page, Integer perPage, String filtroGeneral,
			 Integer codigoSolicitud) throws BOException;
}

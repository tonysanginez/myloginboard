package com.tasm.bo;

import java.util.Map;

import com.tasm.exceptions.BOException;

public interface ITipoIdentificacionBO {
	/**
	 * 
	 * Obtener todos los tipos de identificación.
	 * 
	 * @param codigoEmpresa
	 * @param page
	 * @param perPage
	 * @param filtroGeneral
	 * @param codigoTipoIdentificacion
	 * @param nemonico
	 * @param nombreTipoIdentificacion
	 * @param estado
	 * @return
	 * @throws BOException
	 */
	public Map<String, Object> obtenerTiposIdentificacion(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			  Integer codigoTipoIdentificacion,
			  String nemonico,
			  String nombreTipoIdentificacion,
			  String estado
			  ) throws BOException;

}

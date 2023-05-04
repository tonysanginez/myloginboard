package com.tasm.bo;

import java.util.Map;

import com.tasm.dto.InputTipoPresentacionDTO;
import com.tasm.dto.InputUnidadesMedidaDTO;
import com.tasm.exceptions.BOException;

public interface ITipoPresentacionBO {
	public Map<String, Object> obtenerTiposPresentacion(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			String nombreTipoPresentacion, Integer codigoTipoPresentacion,  String estado) throws BOException;
	 public void crearTipoPresentacion(InputTipoPresentacionDTO objTipoPresentacionDTO) throws BOException;
	 public void editarTipoPresentacion(Integer codigoTipoPresentacion, InputTipoPresentacionDTO objTipoPresentacionDTO) throws BOException;
	    
	 public void eliminarTipoPresentacion(Integer codigoTipoPresentacion) throws BOException;
}

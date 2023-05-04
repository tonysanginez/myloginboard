package com.tasm.bo;

import java.util.Map;

import com.tasm.dto.InputMarcaDTO;
import com.tasm.dto.InputProductoDTO;
import com.tasm.exceptions.BOException;

public interface IMarcaBO {
	public Map<String, Object> obtenerMarcas(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			String nombreMarca, Integer codigoMarca,  String estado) throws BOException;
	public void crearMarca(InputMarcaDTO objMarcaDTO) throws BOException;
	public void editarMarca(Integer codigoMarca, InputMarcaDTO objMarcaDTO) throws BOException;    
	public void eliminarMarca(Integer codigoMarca) throws BOException;
}

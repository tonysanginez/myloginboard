package com.tasm.bo.impl;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasm.bo.IClienteBO;
import com.tasm.bo.IInventarioBO;
import com.tasm.dao.ComClientesDAO;
import com.tasm.dao.MgiDetallesMovimientoInventarioDAO;
import com.tasm.dao.MgiMovimientoInventarioDAO;
import com.tasm.dao.MgiTipoDocumentoDAO;
import com.tasm.dto.InputClienteDTO;
import com.tasm.dto.InputDetalleMovimientoInventarioDTO;
import com.tasm.dto.InputMovimientoInventarioDTO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.RestClientException;
import com.tasm.model.com.ComClientes;
import com.tasm.util.GenericUtil;

@Service
public class InventarioBOImpl implements IInventarioBO{
	
	@Autowired
	private MgiMovimientoInventarioDAO objMgiMovimientoInventarioDAO;
	
	@Autowired
	private MgiDetallesMovimientoInventarioDAO objMgiDetallesMovimientoInventarioDAO;

	@Override
	public Map<String, Object> obtenerMovimientoInventario(Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, String codigoMovimientoInventario, String codigoTipoDocumento, String descripcion,
			String codigoInstitucion, String referenciaComprobante, String estado) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearMovimientoInventario(InputMovimientoInventarioDTO objMovimientoInventarioDTO) throws BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editarMovimientoInventario(Long codigoMovimientoInventario,
			InputMovimientoInventarioDTO objMovimientoInventarioDTO) throws BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarMovimientoInventario(Long codigoMovimientoInventario) throws BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> obtenerDetallesMovimientoInventario(Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, String lineaDetalle, String codigoMovimientoInventario, String codigoTipoDocumento,
			String codigoProducto, String estado) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearDetallesMovimientoInventario(InputDetalleMovimientoInventarioDTO objDetalleMovimientoInventarioDTO)
			throws BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editarDetallesMovimientoInventario(Long lineaDetalle,
			InputDetalleMovimientoInventarioDTO objDetalleMovimientoInventarioDTO) throws BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarDetallesMovimientoInventario(Long lineaDetalle) throws BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> obtenerTipoDocumento(Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, String tipoDocumento, String descripcion, String codigoOrigenMovimiento,
			String registroManual, String estado) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

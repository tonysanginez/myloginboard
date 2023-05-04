package com.tasm.bo;

import java.util.Map;

import com.tasm.dto.InputDetalleMovimientoInventarioDTO;
import com.tasm.dto.InputMovimientoInventarioDTO;
import com.tasm.exceptions.BOException;

public interface IInventarioBO {
	public Map<String, Object> obtenerMovimientoInventario(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			  String codigoMovimientoInventario,
			  String codigoTipoDocumento,
			  String descripcion,
			  String codigoInstitucion,
			  String referenciaComprobante,
			  String estado
			  ) throws BOException;
	
    public void crearMovimientoInventario(InputMovimientoInventarioDTO objMovimientoInventarioDTO) throws BOException;
    
    public void editarMovimientoInventario(Long codigoMovimientoInventario, InputMovimientoInventarioDTO objMovimientoInventarioDTO) throws BOException;
    
    public void eliminarMovimientoInventario(Long codigoMovimientoInventario) throws BOException;
    
    public Map<String, Object> obtenerDetallesMovimientoInventario(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			  String lineaDetalle,
    		  String codigoMovimientoInventario,
			  String codigoTipoDocumento,
			  String codigoProducto,
			  String estado
			  ) throws BOException;
	
  public void crearDetallesMovimientoInventario(InputDetalleMovimientoInventarioDTO objDetalleMovimientoInventarioDTO) throws BOException;
  
  public void editarDetallesMovimientoInventario(Long lineaDetalle, InputDetalleMovimientoInventarioDTO objDetalleMovimientoInventarioDTO) throws BOException;
  
  public void eliminarDetallesMovimientoInventario(Long lineaDetalle) throws BOException;
  
  
  public Map<String, Object> obtenerTipoDocumento(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
		  String tipoDocumento,
		  String descripcion,
		  String codigoOrigenMovimiento,
		  String registroManual,
		  String estado
		  ) throws BOException;
}

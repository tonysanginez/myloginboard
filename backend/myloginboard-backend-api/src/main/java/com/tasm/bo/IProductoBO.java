package com.tasm.bo;

import java.util.Map;

import com.tasm.dto.InputProductoDTO;
import com.tasm.dto.InputTiposProductosDTO;
import com.tasm.dto.InputUnidadesMedidaDTO;
import com.tasm.exceptions.BOException;

public interface IProductoBO {
    
    /**
     * Obtener todos los productos.
     *
     * @param codigoEmpresa
     * @param page
     * @param perPage
     * @param tipoFiltro
     * @param valorFiltro
     * @param estado
     * @param aplicaIva
     * @throws BOException
     */
    public Map<String, Object> obtenerProductos(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String codigoProducto, String nombreProducto, String descripcion, Integer codigoTipoProducto, Integer codigoTipoPresentacion,
            Integer codigoMarca, Integer codigoUnidadMedida, String estado, String aplicaIva, String codigoBarra, String aplicaStock) throws BOException;

    /**
     * Crear un producto.
     * @param objProductoDTO
     * @throws BOException
     */
    public void crearProducto(InputProductoDTO objProductoDTO) throws BOException;
    
    /**
     * Editar un producto.
     * @param codigoProducto
     * @param objProductoDTO
     * @throws BOException
     */
    public void editarProducto(Long codigoProducto, InputProductoDTO objProductoDTO) throws BOException;
    
    /**
     * Eliminar un producto.
     * @param codigoProducto
     * @throws BOException
     */
    public void eliminarProducto(Long codigoProducto) throws BOException;
    
    
    /**
     * Obtener Tipo Producto.
     *
     * @param objProductoDTO
     * @throws BOException
     */
    public Map<String, Object> obtenerTiposProductos(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String nombreTipoProducto, Long codigoTipoProducto,String estado) throws BOException;
    
    public void crearTiposProducto(InputTiposProductosDTO objTiposProductoDTO) throws BOException;
    
    public void editarTiposProducto(Long codigoTipoProducto, InputTiposProductosDTO objTiposProductoDTO) throws BOException;
    
    public void eliminarTiposProducto(Long codigoTipoProducto) throws BOException;
    
    
    //UNIDADES MEDIDA
    public Map<String, Object> obtenerUnidadesMedida(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String nombreUnidadMedida, Integer codigoUnidadMedida, String abreviatura, String estado) throws BOException;    
    
    public void crearUnidadesMedida(InputUnidadesMedidaDTO objUnidadesMedidaDTO) throws BOException;
    
    public void editarUnidadesMedida(Integer codigoUnidadMedida, InputUnidadesMedidaDTO objUnidadesMedidaDTO) throws BOException;
    
    public void eliminarUnidadesMedida(Integer codigoUnidadMedida) throws BOException;
    
    

}

package com.tasm.bo.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasm.bo.IProductoBO;
import com.tasm.dao.ComProductosDAO;
import com.tasm.dao.MgiTiposProductosDAO;
import com.tasm.dao.MgiUnidadesMedidaDAO;
import com.tasm.dto.InputMarcaDTO;
import com.tasm.dto.InputProductoDTO;
import com.tasm.dto.InputTiposProductosDTO;
import com.tasm.dto.InputUnidadesMedidaDTO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.RestClientException;
import com.tasm.model.com.ComProductos;
import com.tasm.model.mgi.MgiMarcas;
import com.tasm.model.mgi.MgiTiposProductos;
import com.tasm.model.mgi.MgiUnidadesMedida;
import com.tasm.util.GenericUtil;

import java.util.Optional;
import org.apache.commons.lang3.ObjectUtils;

@Service
public class ProductosBOImpl implements IProductoBO {
    
    @Autowired
    private ComProductosDAO objComProductosDAO; 
    @Autowired
    private MgiTiposProductosDAO objMgiTiposProductosDAO;
    @Autowired
    private MgiUnidadesMedidaDAO objMgiUnidadesMedidaDAO;
    
    @Override
    public Map<String, Object> obtenerProductos(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String codigoProducto, String nombreProducto, String descripcion, Integer codigoTipoProducto, Integer codigoTipoPresentacion,
            Integer codigoMarca, Integer codigoUnidadMedida, String estado, String aplicaIva, String aplicaStock, String codigoBarra) throws BOException {
        GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
        GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
        GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
        
        page = page <= 0 ? 1 : page;
        perPage = perPage <= 0 ? 10 : perPage;
            
        return objComProductosDAO.obtenerProductos(codigoEmpresa, page, perPage, filtroGeneral, codigoProducto, nombreProducto, 
                descripcion, codigoTipoProducto, codigoTipoPresentacion, codigoMarca, codigoUnidadMedida, estado, aplicaIva, aplicaStock, codigoBarra);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void crearProducto(InputProductoDTO objProductoDTO) throws BOException {
        GenericUtil.validarCampoRequeridoBO(objProductoDTO.getCodigoEmpresa() != null ?
                objProductoDTO.getCodigoEmpresa().toString() : null, "tasm.campos.codigoEmpresa");
        GenericUtil.validarCampoRequeridoBO(objProductoDTO.getCodigoTipoProducto()!= null ?
                objProductoDTO.getCodigoTipoProducto().toString() : null, "tasm.campos.codigoTipoProducto");
        GenericUtil.validarCampoRequeridoBO(objProductoDTO.getCodigoTipoPresentacion()!= null ?
                objProductoDTO.getCodigoTipoPresentacion().toString() : null, "tasm.campos.codigoTipoPresentacion");
        GenericUtil.validarCampoRequeridoBO(objProductoDTO.getCodigoMarca()!= null ?
                objProductoDTO.getCodigoMarca().toString() : null, "tasm.campos.codigoMarca");
        GenericUtil.validarCampoRequeridoBO(objProductoDTO.getCodigoUnidadMedida()!= null ?
                objProductoDTO.getCodigoUnidadMedida().toString() : null, "tasm.campos.codigoUnidadMedida");
        GenericUtil.validarCampoRequeridoBO(objProductoDTO.getNombreProducto(), "tasm.campos.nombreProducto");
        
        //existe tipoProducto
        //existe tipoPresentacion
        //existe marca
        //existe unidadMedida
        
        ComProductos objComProductos = new ComProductos();
        objComProductos.setCodigoProducto(objComProductosDAO.nextCodigoProducto());
        objComProductos.setCodigoEmpresa(objProductoDTO.getCodigoEmpresa());
        objComProductos.setNombreProducto(objProductoDTO.getNombreProducto());
        objComProductos.setDescripcion(objProductoDTO.getDescripcion());
        objComProductos.setCodigoTipoProducto(objProductoDTO.getCodigoTipoProducto());
        objComProductos.setCodigoTipoPresentacion(objProductoDTO.getCodigoTipoPresentacion());
        objComProductos.setCodigoMarca(objProductoDTO.getCodigoMarca());
        objComProductos.setCodigoUnidadMedida(objProductoDTO.getCodigoUnidadMedida());
        objComProductos.setCodigoBarra(objProductoDTO.getCodigoBarra());
        objComProductos.setAplicaIva(objProductoDTO.getAplicaIva());
        objComProductos.setAplicaStock(objProductoDTO.getAplicaStock());
        objComProductos.setEstado(objProductoDTO.getEstado());
        objComProductos.setUsuarioIngreso(objProductoDTO.getUsuarioIngreso());
        objComProductos.setFechaIngreso(new Date());
        
        objComProductosDAO.persist(objComProductos);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void editarProducto(Long codigoProducto, InputProductoDTO objProductoDTO) throws BOException {
        Optional<ComProductos> optComProducto = objComProductosDAO.find(codigoProducto);
        if (optComProducto.isPresent()) {
            ComProductos comProducto = optComProducto.get();
            if (!ObjectUtils.isEmpty(objProductoDTO.getNombreProducto()))
                comProducto.setNombreProducto(objProductoDTO.getNombreProducto());
            if (!ObjectUtils.isEmpty(objProductoDTO.getDescripcion()))
                comProducto.setDescripcion(objProductoDTO.getDescripcion());
            
            if (objProductoDTO.getCodigoTipoProducto() != null)
                comProducto.setCodigoTipoProducto(objProductoDTO.getCodigoTipoProducto());
            if (objProductoDTO.getCodigoTipoPresentacion()!= null)
                comProducto.setCodigoTipoPresentacion(objProductoDTO.getCodigoTipoPresentacion());
            if (objProductoDTO.getCodigoMarca()!= null)
                comProducto.setCodigoMarca(objProductoDTO.getCodigoMarca());
            if (objProductoDTO.getCodigoUnidadMedida()!= null)
                comProducto.setCodigoUnidadMedida(objProductoDTO.getCodigoUnidadMedida());
            
            if (!ObjectUtils.isEmpty(objProductoDTO.getCodigoBarra()))
                comProducto.setCodigoBarra(objProductoDTO.getCodigoBarra());
            if (!ObjectUtils.isEmpty(objProductoDTO.getAplicaIva()))
                comProducto.setAplicaIva(objProductoDTO.getAplicaIva());
            if (!ObjectUtils.isEmpty(objProductoDTO.getAplicaStock()))
                comProducto.setAplicaStock(objProductoDTO.getAplicaStock());
            if (!ObjectUtils.isEmpty(objProductoDTO.getEstado()))
                comProducto.setEstado(objProductoDTO.getEstado());
            
            objComProductosDAO.update(comProducto);
            
        }else {
            throw new BOException("tasm.warn.productoNoExiste");
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void eliminarProducto(Long codigoProducto) throws BOException {
        Optional<ComProductos> optComProducto = objComProductosDAO.find(codigoProducto);
        if (optComProducto.isPresent()) {
            objComProductosDAO.remove(optComProducto.get());
        }else {
            throw new BOException("tasm.warn.productoNoExiste");
        }
    }
    
    @Override
    public Map<String, Object> obtenerTiposProductos(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String nombreTipoProducto, Long codigoTipoProducto,String estado) throws BOException {
        GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
        GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
        GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
        
        page = page <= 0 ? 1 : page;
        perPage = perPage <= 0 ? 10 : perPage;
            
		return objMgiTiposProductosDAO.obtenerTipoProductos(codigoEmpresa, page, perPage, nombreTipoProducto, filtroGeneral,
				codigoTipoProducto, estado);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void crearTiposProducto(InputTiposProductosDTO objTipoProductoDTO) throws BOException {
        GenericUtil.validarCampoRequeridoBO(objTipoProductoDTO.getCodigoEmpresa() != null ?
        		objTipoProductoDTO.getCodigoEmpresa().toString() : null, "tasm.campos.codigoEmpresa"); 
        GenericUtil.validarCampoRequeridoBO(objTipoProductoDTO.getNombreTipoProducto(), "tasm.campos.nombreTipoProducto");
        
         
        
        MgiTiposProductos MgiTiposProductos = new MgiTiposProductos();
        MgiTiposProductos.setCodigoTipoProducto(objMgiTiposProductosDAO.nextCodigoTipoProducto());
        MgiTiposProductos.setCodigoEmpresa(objTipoProductoDTO.getCodigoEmpresa());
        MgiTiposProductos.setNombreTipoProducto(objTipoProductoDTO.getNombreTipoProducto()); 
        MgiTiposProductos.setEstado(objTipoProductoDTO.getEstado());
        MgiTiposProductos.setUsuarioIngreso(objTipoProductoDTO.getUsuarioIngreso());
        MgiTiposProductos.setFechaIngreso(new Date());
        
        objMgiTiposProductosDAO.persist(MgiTiposProductos);
        
    }
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void editarTiposProducto(Long codigoTipoProducto, InputTiposProductosDTO objTipoProductoDTO) throws BOException {
        Optional<MgiTiposProductos> optMgiTiposProducto = objMgiTiposProductosDAO.find(codigoTipoProducto);
        if (optMgiTiposProducto.isPresent()) {
        	MgiTiposProductos mgiTiposProducto = optMgiTiposProducto.get();
            if (!ObjectUtils.isEmpty(objTipoProductoDTO.getNombreTipoProducto()))
            	mgiTiposProducto.setNombreTipoProducto(objTipoProductoDTO.getNombreTipoProducto());
            if (!ObjectUtils.isEmpty(objTipoProductoDTO.getEstado()))
            	mgiTiposProducto.setEstado(objTipoProductoDTO.getEstado());
            
            objMgiTiposProductosDAO.update(mgiTiposProducto);
            
        }else {
            throw new BOException("tasm.warn.tipoProductoNoExiste");
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void eliminarTiposProducto(Long codigoTipoProducto) throws BOException {
        Optional<MgiTiposProductos> optMgiTiposProducto = objMgiTiposProductosDAO.find(codigoTipoProducto);
        if (optMgiTiposProducto.isPresent()) {
        	objMgiTiposProductosDAO.remove(optMgiTiposProducto.get());
        }else {
            throw new BOException("tasm.warn.tipoProductoNoExiste");
        }
    }
    
    public Map<String, Object> obtenerUnidadesMedida(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String nombreUnidadMedida, Integer codigoUnidadMedida, String abreviatura, String estado) throws BOException{
                GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
                GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
                GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
                
                page = page <= 0 ? 1 : page;
                perPage = perPage <= 0 ? 10 : perPage;
                    
        		return objMgiUnidadesMedidaDAO.obtenerUnidadesMedida(codigoEmpresa, page, perPage, filtroGeneral, nombreUnidadMedida, codigoUnidadMedida, abreviatura, estado);
            }
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void crearUnidadesMedida(InputUnidadesMedidaDTO objUnidadesMedidaDTO) throws BOException{
        GenericUtil.validarCampoRequeridoBO(objUnidadesMedidaDTO.getCodigoEmpresa() != null ?
        		objUnidadesMedidaDTO.getCodigoEmpresa().toString() : null, "tasm.campos.codigoEmpresa"); 
        GenericUtil.validarCampoRequeridoBO(objUnidadesMedidaDTO.getNombreUnidadMedida(), "tasm.campos.nombreUnidadMedida");
        GenericUtil.validarCampoRequeridoBO(objUnidadesMedidaDTO.getAbreviatura(), "tasm.campos.abreviatura");
        
         
        
        MgiUnidadesMedida MgiUnidadesMedida = new MgiUnidadesMedida();
        MgiUnidadesMedida.setCodigoUnidadMedida(objMgiUnidadesMedidaDAO.nextCodigoUnidadMedida());
        MgiUnidadesMedida.setCodigoEmpresa(objUnidadesMedidaDTO.getCodigoEmpresa());
        MgiUnidadesMedida.setNombreUnidadMedida(objUnidadesMedidaDTO.getNombreUnidadMedida()); 
        MgiUnidadesMedida.setAbreviatura(objUnidadesMedidaDTO.getAbreviatura()); 
        MgiUnidadesMedida.setEstado(objUnidadesMedidaDTO.getEstado());
        MgiUnidadesMedida.setUsuarioIngreso(objUnidadesMedidaDTO.getUsuarioIngreso());
        MgiUnidadesMedida.setFechaIngreso(new Date());
        
        objMgiUnidadesMedidaDAO.persist(MgiUnidadesMedida);
        
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void editarUnidadesMedida(Integer codigoUnidadMedida, InputUnidadesMedidaDTO objUnidadesMedidaDTO) throws BOException {
        Optional<MgiUnidadesMedida> optMgiUnidadesMedida = objMgiUnidadesMedidaDAO.find(codigoUnidadMedida);
        if (optMgiUnidadesMedida.isPresent()) {
        	MgiUnidadesMedida mgiUnidadesMedida = optMgiUnidadesMedida.get();
            if (!ObjectUtils.isEmpty(objUnidadesMedidaDTO.getNombreUnidadMedida()))
            	mgiUnidadesMedida.setNombreUnidadMedida(objUnidadesMedidaDTO.getNombreUnidadMedida());
            if (!ObjectUtils.isEmpty(objUnidadesMedidaDTO.getEstado()))
            	mgiUnidadesMedida.setEstado(objUnidadesMedidaDTO.getEstado());
            if (!ObjectUtils.isEmpty(objUnidadesMedidaDTO.getAbreviatura()))
            	mgiUnidadesMedida.setAbreviatura(objUnidadesMedidaDTO.getAbreviatura());
            
            objMgiUnidadesMedidaDAO.update(mgiUnidadesMedida);
            
        }else {
            throw new BOException("tasm.warn.unidadMedidaNoExiste");
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void eliminarUnidadesMedida(Integer codigoUnidadMedida) throws BOException {
        Optional<MgiUnidadesMedida> optMgiUnidadesMedida = objMgiUnidadesMedidaDAO.find(codigoUnidadMedida);
        if (optMgiUnidadesMedida.isPresent()) {
        	objMgiUnidadesMedidaDAO.remove(optMgiUnidadesMedida.get());
        }else {
            throw new BOException("tasm.warn.unidadMedidaNoExiste");
        }
    }
    
}

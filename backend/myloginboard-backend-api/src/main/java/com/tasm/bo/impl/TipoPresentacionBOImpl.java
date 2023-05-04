package com.tasm.bo.impl;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasm.bo.ITipoPresentacionBO;
import com.tasm.dao.MgiTipoPresentacionDAO;
import com.tasm.dto.InputTipoPresentacionDTO;
import com.tasm.dto.InputUnidadesMedidaDTO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.RestClientException;
import com.tasm.model.mgi.MgiTipoPresentacion;
import com.tasm.model.mgi.MgiUnidadesMedida;
import com.tasm.util.GenericUtil;

@Service
public class TipoPresentacionBOImpl implements ITipoPresentacionBO{
	@Autowired
    private MgiTipoPresentacionDAO objMgiTipoPresentacionDAO;
	
	@Override
	public Map<String, Object> obtenerTiposPresentacion(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral, String nombreTipoPresentacion,
			Integer codigoTipoPresentacion, String estado) throws BOException {
		 GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
	     GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
	     GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
	     page = page <= 0 ? 1 : page;
	        perPage = perPage <= 0 ? 10 : perPage;
	            
	    return objMgiTipoPresentacionDAO.obtenerTiposPresentacion(codigoEmpresa, page, perPage, filtroGeneral, nombreTipoPresentacion, 
	              codigoTipoPresentacion, estado);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearTipoPresentacion(InputTipoPresentacionDTO objTipoPresentacionDTO) throws BOException {
		GenericUtil.validarCampoRequeridoBO(objTipoPresentacionDTO.getCodigoEmpresa() != null ?
				objTipoPresentacionDTO.getCodigoEmpresa().toString() : null, "tasm.campos.codigoEmpresa");
        //GenericUtil.validarCampoRequeridoBO(objTipoPresentacionDTO.getCodigoTipoPresentacion()!= null ?
        //		objTipoPresentacionDTO.getCodigoTipoPresentacion().toString() : null, "tasm.campos.codigoTipoPresentacion");
        GenericUtil.validarCampoRequeridoBO(objTipoPresentacionDTO.getNombreTipoPresentacion(), "tasm.campos.nombreTipoPresentacion");
        
        //existe marca
        
        MgiTipoPresentacion objMgiTipoPresentacion = new MgiTipoPresentacion();
        //objMgiTipoPresentacion.setCodigoTipoPresentacion(objTipoPresentacionDTO.getCodigoTipoPresentacion());
        objMgiTipoPresentacion.setCodigoTipoPresentacion(objMgiTipoPresentacionDAO.nextCodigoTipoPresentacion());
        objMgiTipoPresentacion.setCodigoEmpresa(objTipoPresentacionDTO.getCodigoEmpresa());
        objMgiTipoPresentacion.setNombreTipoPresentacion(objTipoPresentacionDTO.getNombreTipoPresentacion());
        objMgiTipoPresentacion.setEstado(objTipoPresentacionDTO.getEstado());
        objMgiTipoPresentacion.setUsuarioIngreso(objTipoPresentacionDTO.getUsuarioIngreso());
        objMgiTipoPresentacion.setFechaIngreso(new Date());
        
        objMgiTipoPresentacionDAO.persist(objMgiTipoPresentacion);
        
	}
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void editarTipoPresentacion(Integer codigoTipoPresentacion, InputTipoPresentacionDTO objTipoPresentacionDTO) throws BOException {
        Optional<MgiTipoPresentacion> optMgiTiposPresentacion = objMgiTipoPresentacionDAO.find(codigoTipoPresentacion);
        if (optMgiTiposPresentacion.isPresent()) {
        	MgiTipoPresentacion mgiTiposPresentacion = optMgiTiposPresentacion.get();
            if (!ObjectUtils.isEmpty(objTipoPresentacionDTO.getNombreTipoPresentacion()))
            	mgiTiposPresentacion.setNombreTipoPresentacion(objTipoPresentacionDTO.getNombreTipoPresentacion());
            if (!ObjectUtils.isEmpty(objTipoPresentacionDTO.getEstado()))
            	mgiTiposPresentacion.setEstado(objTipoPresentacionDTO.getEstado());
            
            objMgiTipoPresentacionDAO.update(mgiTiposPresentacion);
            
        }else {
            throw new BOException("tasm.warn.tipoPresentacionNoExiste");
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void eliminarTipoPresentacion(Integer codigoTipoPresentacion) throws BOException {
        Optional<MgiTipoPresentacion> optMgiTiposPresentacion = objMgiTipoPresentacionDAO.find(codigoTipoPresentacion);
        if (optMgiTiposPresentacion.isPresent()) {
        	objMgiTipoPresentacionDAO.remove(optMgiTiposPresentacion.get());
        }else {
            throw new BOException("tasm.warn.tipoPresentacionNoExiste");
        }
    }
}

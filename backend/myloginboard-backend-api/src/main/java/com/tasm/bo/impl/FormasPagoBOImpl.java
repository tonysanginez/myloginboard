package com.tasm.bo.impl;

import java.util.Date;
import java.util.Map;
import java.util.Optional;


import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasm.bo.IFormasPagoBO;
import com.tasm.dao.GenFormasPagoDAO;
import com.tasm.dto.FormasPagoDTO;
import com.tasm.dto.InputCrearFormasPagoDTO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.RestClientException;
import com.tasm.model.gen.GenFormasPago;
import com.tasm.security.UsuarioLogin;
import com.tasm.util.GenericUtil;

@Service
public class FormasPagoBOImpl implements IFormasPagoBO{
	
    @Autowired
    private GenFormasPagoDAO objGenFormasPagoDAO; 

	@Override
	public Map<String, Object> obtenerFormasPago(Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, Integer codigoFormaPago, String nemonico, String nombre_forma_pago, String codigo_sri,
			String estado)throws BOException {
		GenericUtil.validarCampoRequeridoBO(codigoEmpresa, "tasm.campos.codigoEmpresa");
		GenericUtil.validarRequeridoMayorACero(page, "tasm.campos.page");
		GenericUtil.validarRequeridoMayorACero(perPage, "tasm.campos.perPage");
		GenericUtil.validarCampoRequeridoBO(estado, "tasm.campos.estado");
        return objGenFormasPagoDAO.obtenerFormasPago(codigoEmpresa, page, perPage, filtroGeneral, codigoFormaPago, nemonico, 
        		nombre_forma_pago, codigo_sri, estado);
    }

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearFormasPago(InputCrearFormasPagoDTO objInputFormasPagoDTO,UsuarioLogin objUsuarioLogin) throws BOException {
        GenericUtil.validarCampoRequeridoBO(objInputFormasPagoDTO.getCodigoEmpresa() != null ?
        		objInputFormasPagoDTO.getCodigoEmpresa().toString() : null, "tasm.campos.codigoEmpresa");
        GenericUtil.validarCampoRequeridoBO(objInputFormasPagoDTO.getNemonico(), "tasm.campos.nombreNemonico");
        GenericUtil.validarCampoRequeridoBO(objInputFormasPagoDTO.getNombreFormaPago(), "tasm.campos.nombreFormaPago");  
        GenericUtil.validarCantidadCaracteresCampo(objInputFormasPagoDTO.getCodigoSri(),"tasm.campos.codigoSri",2);
        GenFormasPago objGenFormasPago = new GenFormasPago();
        objGenFormasPago.setCodigoEmpresa(objInputFormasPagoDTO.getCodigoEmpresa());
        objGenFormasPago.setNemonico(objInputFormasPagoDTO.getNemonico());
        objGenFormasPago.setNombreFormaPago(objInputFormasPagoDTO.getNombreFormaPago());
        objGenFormasPago.setCodigoSri(objInputFormasPagoDTO.getCodigoSri());
        objGenFormasPago.setEstado("A");
        objGenFormasPago.setUsuarioIngreso(objUsuarioLogin.getCodigoUsuario());
        objGenFormasPago.setFechaIngreso(new Date());
        objGenFormasPagoDAO.persist(objGenFormasPago);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void editarFormasPago(Long codigoFormasPago, InputCrearFormasPagoDTO objInputFormasPagoDTO,UsuarioLogin objUsuarioLogin)
			throws BOException {
		Optional<GenFormasPago> optGenFormasPago = objGenFormasPagoDAO.find(codigoFormasPago);
        if (optGenFormasPago.isPresent()) {   	
        	GenFormasPago objGenFormasPago = optGenFormasPago.get();    	
            if (!ObjectUtils.isEmpty(objInputFormasPagoDTO.getCodigoSri()))
            	objGenFormasPago.setCodigoSri(objInputFormasPagoDTO.getCodigoSri());
            if (!ObjectUtils.isEmpty(objInputFormasPagoDTO.getNombreFormaPago()))
            	objGenFormasPago.setNombreFormaPago(objInputFormasPagoDTO.getNombreFormaPago());    
            if (!ObjectUtils.isEmpty(objInputFormasPagoDTO.getNemonico()))
            	objGenFormasPago.setNemonico(objInputFormasPagoDTO.getNemonico()); 
            if (objInputFormasPagoDTO.getCodigoEmpresa()!= null)
            	objGenFormasPago.setCodigoEmpresa(objInputFormasPagoDTO.getCodigoEmpresa());
            if (!ObjectUtils.isEmpty(objInputFormasPagoDTO.getEstado()))
            	objGenFormasPago.setEstado(objInputFormasPagoDTO.getEstado());
            GenericUtil.validarCantidadCaracteresCampo(objInputFormasPagoDTO.getCodigoSri(),"tasm.campos.codigoSri",2);
            objGenFormasPago.setFechaModificacion(new Date());
            objGenFormasPago.setUsuarioModificacion(objUsuarioLogin.getCodigoUsuario());
            objGenFormasPagoDAO.update(objGenFormasPago);
        }else {
            throw new BOException("tasm.warn.formaspagoNoExiste");
        }
		
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void eliminarFormasPago(Long codigoFormasPago,UsuarioLogin objUsuarioLogin) throws BOException {
        Optional<GenFormasPago> optGenFormasPago = objGenFormasPagoDAO.find(codigoFormasPago);
        if (optGenFormasPago.isPresent()) {
        	GenFormasPago objGenFormasPago = optGenFormasPago.get();
        	objGenFormasPago.setEstado("I");
        	objGenFormasPago.setUsuarioModificacion(objUsuarioLogin.getCodigoUsuario());
        	objGenFormasPago.setFechaModificacion(new Date());
        	objGenFormasPagoDAO.update(objGenFormasPago);
        }else {
            throw new BOException("tasm.warn.formaspagoNoExiste");
        }
    }

	@Override
	public FormasPagoDTO obtenerUnaFormasPagos(Long codigoFormasPago, UsuarioLogin objUsuarioLogin) throws BOException {
        Optional<GenFormasPago> optGenFormasPago = objGenFormasPagoDAO.find(codigoFormasPago);
        if (optGenFormasPago.isPresent()) {
        	GenFormasPago objGenFormasPago = optGenFormasPago.get();
        	FormasPagoDTO objFormasPago = new FormasPagoDTO();
            objFormasPago.setCodigoFormasPago(objGenFormasPago.getCodigoFormasPago());
            objFormasPago.setNemonico(objGenFormasPago.getNemonico());
            objFormasPago.setNombreFormaPago(objGenFormasPago.getNombreFormaPago());
            objFormasPago.setCodigoSri(objGenFormasPago.getCodigoSri());
            objFormasPago.setEstado(objGenFormasPago.getEstado());
            objFormasPago.setFechaIngreso(objGenFormasPago.getFechaIngreso().toString());
        	return objFormasPago;
        }else {
            throw new BOException("tasm.warn.formaspagoNoExiste");
        }
	}

	

}

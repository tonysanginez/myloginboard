package com.tasm.bo.impl;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasm.bo.IMarcaBO;
import com.tasm.dao.MgiMarcasDAO;
import com.tasm.dto.InputMarcaDTO;
import com.tasm.dto.InputProductoDTO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.RestClientException;
import com.tasm.model.com.ComProductos;
import com.tasm.model.mgi.MgiMarcas;
import com.tasm.util.GenericUtil;
@Service
public class MarcasBOImpl implements IMarcaBO{
	@Autowired
    private MgiMarcasDAO objMgiMarcasDAO;
	
	@Override
	public Map<String, Object> obtenerMarcas(Short codigoEmpresa, Integer page, Integer perPage, String nombreMarca, String filtroGeneral,
			Integer codigoMarca, String estado) throws BOException {
		 GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
	     GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
	     GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
	     page = page <= 0 ? 1 : page;
	        perPage = perPage <= 0 ? 10 : perPage;
	            
	    return objMgiMarcasDAO.obtenerMarcas(codigoEmpresa, page, perPage, nombreMarca, filtroGeneral,
	              codigoMarca, estado);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearMarca(InputMarcaDTO objMarcaDTO) throws BOException {
		GenericUtil.validarCampoRequeridoBO(objMarcaDTO.getCodigoEmpresa() != null ?
				objMarcaDTO.getCodigoEmpresa().toString() : null, "tasm.campos.codigoEmpresa");
        //GenericUtil.validarCampoRequeridoBO(objMarcaDTO.getCodigoMarca()!= null ?
        //		objMarcaDTO.getCodigoMarca().toString() : null, "tasm.campos.codigoMarca");
        GenericUtil.validarCampoRequeridoBO(objMarcaDTO.getNombreMarca(), "tasm.campos.nombreMarca");
        
        //existe marca
        
        MgiMarcas objMgiMarcas = new MgiMarcas();
        objMgiMarcas.setCodigoMarca(objMgiMarcasDAO.nextCodigoMarca());
        objMgiMarcas.setCodigoEmpresa(objMarcaDTO.getCodigoEmpresa());
        objMgiMarcas.setNombreMarca(objMarcaDTO.getNombreMarca());
        objMgiMarcas.setEstado(objMarcaDTO.getEstado());
        objMgiMarcas.setUsuarioIngreso(objMarcaDTO.getUsuarioIngreso());
        objMgiMarcas.setFechaIngreso(new Date());
        
        objMgiMarcasDAO.persist(objMgiMarcas);
        
	}
	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void editarMarca(Integer codigoMarca, InputMarcaDTO objMarcaDTO) throws BOException {
        Optional<MgiMarcas> optMgiMarca = objMgiMarcasDAO.find(codigoMarca);
        if (optMgiMarca.isPresent()) {
        	MgiMarcas mgiMarca = optMgiMarca.get();
            if (!ObjectUtils.isEmpty(objMarcaDTO.getNombreMarca()))
                mgiMarca.setNombreMarca(objMarcaDTO.getNombreMarca());
            if (!ObjectUtils.isEmpty(objMarcaDTO.getEstado()))
            	mgiMarca.setEstado(objMarcaDTO.getEstado());
            
            objMgiMarcasDAO.update(mgiMarca);
            
        }else {
            throw new BOException("tasm.warn.marcaNoExiste");
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
    public void eliminarMarca(Integer codigoMarca) throws BOException {
        Optional<MgiMarcas> optMgiMarca = objMgiMarcasDAO.find(codigoMarca);
        if (optMgiMarca.isPresent()) {
        	objMgiMarcasDAO.remove(optMgiMarca.get());
        }else {
            throw new BOException("tasm.warn.marcaNoExiste");
        }
    }

}

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
import com.tasm.dao.ComClientesDAO;
import com.tasm.dto.InputClienteDTO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.RestClientException;
import com.tasm.model.com.ComClientes;
import com.tasm.util.GenericUtil;

@Service
public class ClientesBOImpl implements IClienteBO{
	
	@Autowired
	private ComClientesDAO objComClientesDAO;
	//@Autowired
	//private GenTiposIdentificacionDAO objGenTiposIdentificacionDAO;
	
	@Override
	public Map<String, Object> obtenerClientes(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			String numeroIdentificacion, String codigoCliente, String razonSocial, String nombreComercial,
			String telefonoMovil, String direccion, String correoElectronico, String nombreCliente, String estado)
			throws BOException {
		  GenericUtil.validarCampoRequeridoBO(codigoEmpresa.toString(), "tasm.campos.codigoEmpresa");
	        GenericUtil.validarCampoRequeridoBO(page.toString(), "tasm.campos.page");
	        GenericUtil.validarCampoRequeridoBO(perPage.toString(), "tasm.campos.perPage");
	        
	        page = page <= 0 ? 1 : page;
	        perPage = perPage <= 0 ? 10 : perPage;
	        return objComClientesDAO.obtenerClientes(codigoEmpresa, page, perPage, filtroGeneral, numeroIdentificacion, 
	        		codigoCliente, razonSocial, nombreComercial, telefonoMovil, direccion, correoElectronico, nombreCliente, estado);
	        
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearCliente(InputClienteDTO objClienteDTO) throws BOException {
		 GenericUtil.validarCampoRequeridoBO(objClienteDTO.getCodigoEmpresa() != null ?
				 objClienteDTO.getCodigoEmpresa().toString() : null, "tasm.campos.codigoEmpresa");
	        GenericUtil.validarCampoRequeridoBO(objClienteDTO.getCodigoTipoIdentificacion()!= null ?
	                objClienteDTO.getCodigoTipoIdentificacion().toString() : null, "tasm.campos.codigoTipoIdentificacion");
	        GenericUtil.validarCampoRequeridoBO(objClienteDTO.getRazonSocial(), "tasm.campos.razonSocial");
	        
	        //existe tipoIdentificacion
	        ComClientes objComClientes = new ComClientes();
	        objComClientes.setCodigoCliente(objComClientesDAO.nextCodigoCliente());
	        objComClientes.setCodigoEmpresa(objClienteDTO.getCodigoEmpresa());
	        objComClientes.setNumeroIdentificacion(objClienteDTO.getNumeroIdentificacion());
	        objComClientes.setRazonSocial(objClienteDTO.getRazonSocial());
	        objComClientes.setNombreComercial(objClienteDTO.getNombreComercial());
	        objComClientes.setTelefonoMovil(objClienteDTO.getTelefonoMovil());
	        objComClientes.setDireccion(objClienteDTO.getDireccion());
	        objComClientes.setCorreoElectronico(objClienteDTO.getCorreoElectronico());
	        objComClientes.setNombreCliente(objClienteDTO.getNombreCliente());
	        objComClientes.setCodigoTipoIdentificacion(objClienteDTO.getCodigoTipoIdentificacion());
	        objComClientes.setEstado(objClienteDTO.getEstado());
	        objComClientes.setUsuarioIngreso(objClienteDTO.getUsuarioIngreso());
	        objComClientes.setFechaIngreso(new Date());
	        
	        //Campos por agregar en el frontend
	        objComClientes.setFechaNacimiento(new Date());//Cambiar por fecha de Nacimiento en el frontend
	        objComClientes.setPrimerApellido("");
	        objComClientes.setPrimerNombre("");
	        objComClientes.setSegundoApellido("");
	        objComClientes.setSegundoNombre("");
	        objComClientes.setSexo("");
	        
	        
	        objComClientesDAO.persist(objComClientes);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void editarCliente(Long codigoCliente, InputClienteDTO objClienteDTO) throws BOException {
		Optional<ComClientes> optComCliente = objComClientesDAO.find(codigoCliente);
        if (optComCliente.isPresent()) {
        	ComClientes comCliente = optComCliente.get();
            if (!ObjectUtils.isEmpty(objClienteDTO.getNumeroIdentificacion()))
                comCliente.setNumeroIdentificacion(objClienteDTO.getNumeroIdentificacion());
            if (!ObjectUtils.isEmpty(objClienteDTO.getRazonSocial()))
                comCliente.setRazonSocial(objClienteDTO.getRazonSocial());
            if (!ObjectUtils.isEmpty(objClienteDTO.getNombreComercial())) 
            	comCliente.setNombreComercial(objClienteDTO.getNombreComercial());
            if (!ObjectUtils.isEmpty(objClienteDTO.getTelefonoMovil())) 
            	comCliente.setTelefonoMovil(objClienteDTO.getTelefonoMovil());
            if (!ObjectUtils.isEmpty(objClienteDTO.getDireccion())) 
            	comCliente.setDireccion(objClienteDTO.getDireccion());
            if (!ObjectUtils.isEmpty(objClienteDTO.getCorreoElectronico())) 
            	comCliente.setCorreoElectronico(objClienteDTO.getCorreoElectronico());
            if (!ObjectUtils.isEmpty(objClienteDTO.getNombreCliente()))
            	comCliente.setNombreCliente(objClienteDTO.getNombreCliente());
            if (!ObjectUtils.isEmpty(objClienteDTO.getCodigoTipoIdentificacion()))
            	comCliente.setCodigoTipoIdentificacion(objClienteDTO.getCodigoTipoIdentificacion());
            if (!ObjectUtils.isEmpty(objClienteDTO.getEstado()))
                comCliente.setEstado(objClienteDTO.getEstado());
            
            objComClientesDAO.update(comCliente);
            
        }else {
            throw new BOException("tasm.warn.clienteNoExiste");
        }
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void eliminarCliente(Long codigoCliente) throws BOException {
	    Optional<ComClientes> optComCliente = objComClientesDAO.find(codigoCliente);
        if (optComCliente.isPresent()) {
        	objComClientesDAO.remove(optComCliente.get());
        }else {
            throw new BOException("tasm.warn.clienteNoExiste");
        }
		
	}

}

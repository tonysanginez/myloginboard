package com.tasm.api;


import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tasm.bo.IProveedoresBO;
import com.tasm.dto.FormasPagoDTO;
import com.tasm.dto.InputCrearProveedorDTO;
import com.tasm.dto.ProveedoresDTO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.security.UsuarioLogin;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/proveedores")
@CrossOrigin
public class ProveedoresApi {
	
	private static final Logger logger = LogManager.getLogger(ProveedoresApi.class.getName());
	
	@Autowired
    private IProveedoresBO objIProveedoresBO;
	
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerProveedores (
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestParam(value = "codigoEmpresa",required = false)  Short codigoEmpresa,
            @RequestParam(value = "codigoInstitucion",required = false)  Long codigoInstitucion,
            @RequestParam(value = "numeroIdentificacion", required = false) String numeroIdentificacion,
            @RequestParam(value = "nombreInstitucion", required = false) String nombreInstitucion,
            @RequestParam(value = "nombreComercial", required = false) String nombreComercial,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "correoElectronico", required = false) String correoElectronico,
            @RequestParam(value = "telefonoMovil", required = false) String telefonoMovil,
            @RequestParam(value = "nombrePersonaContacto", required = false) String nombrePersonaContacto,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "perPage", required = false) Integer perPage,
            @RequestParam(value = "estado", required = false) String estado) throws BOException{ 	 
            try {
            	Map<String, Object> data = objIProveedoresBO
            			.obtenerProveedores(codigoInstitucion,
            					numeroIdentificacion,
            					nombreInstitucion,
            					nombreComercial,
            					direccion,
            					correoElectronico,
            					telefonoMovil,
            					nombrePersonaContacto,
            					codigoEmpresa, page, perPage, filtroGeneral, estado);
                return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                        data), HttpStatus.OK);
            } catch (BOException e) {
                logger.error(e);
                throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
            }
       
    }
	
    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearProveedor(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestBody InputCrearProveedorDTO objInputCrearProveedorDTO) {
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	objIProveedoresBO.crearProveedor(objInputCrearProveedorDTO,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoProveedor}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarProveedor(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoProveedor") Long codigoProveedor,
            @RequestBody InputCrearProveedorDTO objInputCrearProveedorDTO) {
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	objIProveedoresBO.editarProveedor(codigoProveedor,objInputCrearProveedorDTO,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoProveedor}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarProveedor(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoProveedor") Long codigoProveedor) {
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	objIProveedoresBO.eliminarProveedor(codigoProveedor,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoProveedor}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerUnProveedor (
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoProveedor") Long codigoProveedor) {
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	ProveedoresDTO data = objIProveedoresBO.obtenerUnProveedor(codigoProveedor,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
	

}

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
import com.tasm.bo.IFormasPagoBO;
import com.tasm.dto.FormasPagoDTO;
import com.tasm.dto.InputCrearFormasPagoDTO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.security.UsuarioLogin;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/formaspago")
@CrossOrigin
public class FormasPagoApi {

    private static final Logger logger = LogManager.getLogger(FormasPagoApi.class.getName());

    @Autowired
    private IFormasPagoBO objIFormasPagoBO;
	
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerFormasPagos (
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestParam(value = "codigoEmpresa",required = false)  Short codigoEmpresa,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "perPage", required = false) Integer perPage,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "codigoFormasPago", required = false) Integer codigoFormaPago,
            @RequestParam(value = "nemonico", required = false) String nemonico,
            @RequestParam(value = "nombreFormaPago", required = false) String nombreFormaPago,
            @RequestParam(value = "codigoSri", required = false) String codigoSri,
            @RequestParam(value = "estado", required = false) String estado) throws BOException, JsonProcessingException, IOException, RestClientException{
        try {
            Map<String, Object> data = objIFormasPagoBO.obtenerFormasPago(codigoEmpresa, page, perPage, filtroGeneral, codigoFormaPago, nemonico,
            		nombreFormaPago, codigoSri, estado);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearFormasPago(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestBody InputCrearFormasPagoDTO objInputFormasPagoDTO) throws BOException, JsonProcessingException, IOException, RestClientException{
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	objIFormasPagoBO.crearFormasPago(objInputFormasPagoDTO,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
	
    @RequestMapping(value = "/{codigoFormasPago}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarFormasPago(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoFormasPago") Long codigoFormasPago,
            @RequestBody InputCrearFormasPagoDTO objInputFormasPagoDTO) throws BOException, JsonProcessingException, IOException, RestClientException{
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	objIFormasPagoBO.editarFormasPago(codigoFormasPago,objInputFormasPagoDTO,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoFormasPago}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarFormasPago(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoFormasPago") Long codigoFormasPago) throws BOException, JsonProcessingException, IOException, RestClientException{
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	objIFormasPagoBO.eliminarFormasPago(codigoFormasPago,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoFormasPago}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerUnaFormasPagos (
    		@RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoFormasPago") Long codigoFormasPago)throws BOException, JsonProcessingException, IOException, RestClientException{
        try {
        	UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	FormasPagoDTO data = objIFormasPagoBO.obtenerUnaFormasPagos(codigoFormasPago,objUsuarioLogin);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
}

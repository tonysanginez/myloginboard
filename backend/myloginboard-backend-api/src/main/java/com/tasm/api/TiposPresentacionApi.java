package com.tasm.api;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasm.bo.ITipoPresentacionBO;
import com.tasm.dto.InputTipoPresentacionDTO;
import com.tasm.dto.InputUnidadesMedidaDTO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/tiposPresentacion")
@CrossOrigin
public class TiposPresentacionApi {
	private static final Logger logger = LogManager.getLogger(AutenticacionApi.class.getName());
	@Autowired
	private ITipoPresentacionBO objITipoPresentacionBO;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerTiposPresentacion(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestParam(value = "codigoEmpresa", defaultValue = "1")  Short codigoEmpresa,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "nombreTipoPresentacion", required = false) String nombreTipoPresentacion,
            @RequestParam(value = "codigoTipoPresentacion", required = false) Integer codigoTipoPresentacion,
            //@RequestParam(value = "estado", defaultValue = "A", required = false) String estado, //Tony Sanginez 20/09/2022 se modifico porque en el listado solo traia los Activos
            @RequestParam(value = "estado",  required = false) String estado,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order) {
        try {
            Map<String, Object> data = objITipoPresentacionBO.obtenerTiposPresentacion(codigoEmpresa, page, perPage, nombreTipoPresentacion, filtroGeneral,
                     codigoTipoPresentacion,  estado);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
	 @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> crearTipoPresentacion(
	            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
	            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
	            @RequestBody InputTipoPresentacionDTO objTipoPresentacionDTO) {
	        try {
	        	objITipoPresentacionBO.crearTipoPresentacion(objTipoPresentacionDTO);
	            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
	                    null), HttpStatus.OK);
	        } catch (BOException e) {
	            logger.error(e);
	            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
	        }
	    }
	 @RequestMapping(value = "/{codigoTipoPresentacion}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> editarTipoPresentacion(
	            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
	            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
	            @PathVariable("codigoTipoPresentacion") Integer codigoTipoPresentacion,
	            @RequestBody InputTipoPresentacionDTO objTipoPresentacionDTO) {
	        try {
	        	objITipoPresentacionBO.editarTipoPresentacion(codigoTipoPresentacion, objTipoPresentacionDTO);
	        	return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
	                    null), HttpStatus.OK);
	        } catch (BOException e) {
	            logger.error(e);
	            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
	        }
	    }
	    
	    @RequestMapping(value = "/{codigoTipoPresentacion}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> eliminarTipoPresentacion(
	            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
	            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
	            @PathVariable("codigoTipoPresentacion") Integer codigoTipoPresentacion) {
	        try {
	        	objITipoPresentacionBO.eliminarTipoPresentacion(codigoTipoPresentacion);
	            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
	                    null), HttpStatus.OK);
	        } catch (BOException e) {
	            logger.error(e);
	            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
	        }
	    }    
}

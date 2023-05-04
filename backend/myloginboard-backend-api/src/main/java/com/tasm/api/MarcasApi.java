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

import com.tasm.bo.IMarcaBO;
import com.tasm.dto.InputMarcaDTO;
import com.tasm.dto.InputProductoDTO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.MensajesUtil;



@RestController
@RequestMapping("/v1/marcas")
@CrossOrigin
public class MarcasApi {
	private static final Logger logger = LogManager.getLogger(AutenticacionApi.class.getName());
	@Autowired
	private IMarcaBO objIMarcaBO;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerMarcas(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestParam(value = "codigoEmpresa", defaultValue = "1")  Short codigoEmpresa,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "nombreMarca", required = false) String nombreMarca,
            @RequestParam(value = "codigoMarca", required = false) Integer codigoMarca,
            @RequestParam(value = "estado", defaultValue = "A", required = false) String estado,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order) {
        try {
            Map<String, Object> data = objIMarcaBO.obtenerMarcas(codigoEmpresa, page, perPage, filtroGeneral, nombreMarca, 
                     codigoMarca,  estado);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
	 @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> crearMarca(
	            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
	            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
	            @RequestBody InputMarcaDTO objMarcaDTO) {
	        try {
	            objIMarcaBO.crearMarca(objMarcaDTO);
	            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
	                    null), HttpStatus.OK);
	        } catch (BOException e) {
	            logger.error(e);
	            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
	        }
	    }
	 @RequestMapping(value = "/{codigoMarca}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> editarMarca(
	            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
	            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
	            @PathVariable("codigoMarca") Integer codigoMarca,
	            @RequestBody InputMarcaDTO objMarcaDTO) {
	        try {
	        	objIMarcaBO.editarMarca(codigoMarca, objMarcaDTO);
	            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
	                    null), HttpStatus.OK);
	        } catch (BOException e) {
	            logger.error(e);
	            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
	        }
	    }
	    
	    @RequestMapping(value = "/{codigoMarca}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> eliminarMarca(
	            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
	            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
	            @PathVariable("codigoMarca") Integer codigoMarca) {
	        try {
	        	objIMarcaBO.eliminarMarca(codigoMarca);
	            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
	                    null), HttpStatus.OK);
	        } catch (BOException e) {
	            logger.error(e);
	            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
	        }
	    }
}

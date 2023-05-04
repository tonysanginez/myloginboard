package com.tasm.api;

import java.io.IOException;

import javax.ws.rs.QueryParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tasm.bo.IGeneralesBO;
import com.tasm.db.config.DBContextHolder;
import com.tasm.dto.response.ResponseOk;
import com.tasm.enums.DBTypeEnum;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/general")
public class GeneralesApi {
	
	private static final Logger logger = LogManager.getLogger(GeneralesApi.class.getName());
	
	@Autowired
	private IGeneralesBO objGeneralesBO;
 
    @RequestMapping(value = "/parametros",method = RequestMethod.GET)  
   	public ResponseEntity<?> organizacione(
   			@RequestHeader(value = "Accept-Language", required = false) String strLanguage,
   			@QueryParam("codigoEmpresa")  Integer codigoEmpresa, 
            @QueryParam("nemonico") String nemonico  ) 
   	throws   BOException, JsonProcessingException, IOException, RestClientException{ 
//   		GenericUtil.validarCampoRequeridoBO(strToken, "tasm.campos.headerAutorizacion");

   		DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM);
//           UsuarioLogin usuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   		try {
   			// UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
   			return new ResponseEntity<>(
   					new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)), 
   							objGeneralesBO.obtenerParametros(codigoEmpresa, nemonico)), 
   					HttpStatus.OK);
   			
   		} catch (BOException e) {
   			logger.error(e);
   			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
   		}  finally {
   			DBContextHolder.clear();
   		}
   	}
     
	
 
	
}
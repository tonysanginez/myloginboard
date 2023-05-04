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
import com.tasm.bo.IUsuariosBO;
import com.tasm.db.config.DBContextHolder;
import com.tasm.dto.response.ResponseOk;
import com.tasm.dto.response.ResponseUnauthorized;
import com.tasm.enums.DBTypeEnum;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.UnauthorizedException;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/usuario")
public class UsuariosApi {
	
	private static final Logger logger = LogManager.getLogger(UsuariosApi.class.getName());
	
	@Autowired
	private IUsuariosBO objIUsuariosBO;

    @RequestMapping(value = "/opciones",method = RequestMethod.GET)  
	public ResponseEntity<?> menus(
			@RequestHeader(value = "Accept-Language", required = false) String strLanguage, 
            @QueryParam("codigoEmpresa")  Integer codigoEmpresa,
            @QueryParam("codigoSucursal") Integer codigoSucursal, 
            @QueryParam("secuenciaUsuario") Long secuenciaUsuario ) 
	throws   BOException, JsonProcessingException, IOException, RestClientException{
    	
    	
//		GenericUtil.validarCampoRequeridoBO(strToken, "tasm.campos.headerAutorizacion");

		DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM);
//        UsuarioLogin usuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			// UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
			return new ResponseEntity<>(
					new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)), 
							objIUsuariosBO.consultarOpcionesMenuAccesoUsuario(secuenciaUsuario, codigoSucursal, codigoEmpresa.shortValue())), 
					HttpStatus.OK);
			
		} catch (BOException e) {
			logger.error(e);
			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
		} catch (UnauthorizedException e) {
			logger.error(e);
			return new ResponseEntity<>(new ResponseUnauthorized(e.getTranslatedMessage(strLanguage), null), HttpStatus.UNAUTHORIZED);
		} finally {
			DBContextHolder.clear();
		}
	}
    
    
    @RequestMapping(value = "/sucursales",method = RequestMethod.GET)  
  	public ResponseEntity<?> consultarSucursalesXUsuario(
  			@RequestHeader(value = "Accept-Language", required = false) String strLanguage,  
  			@QueryParam("secuenciaUsuario") Long secuenciaUsuario ) 
  	throws   BOException, JsonProcessingException, IOException, RestClientException{
      	 
  		DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM); 
  		try { 
  			return new ResponseEntity<>(
  					new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)), 
  							objIUsuariosBO.consultarSucursalesXUsuario(secuenciaUsuario)), 
  					HttpStatus.OK);
  			
  		} catch (BOException e) {
  			logger.error(e);
  			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData()); 
  		} finally {
  			DBContextHolder.clear();
  		}
  	}
    
    @RequestMapping(value = "/verifica_cuenta",method = RequestMethod.GET)  
   	public ResponseEntity<?> verifica(
   			@RequestHeader(value = "Accept-Language", required = false) String strLanguage,  
   			@QueryParam("usuario") String usuario ) 
   	throws   BOException, JsonProcessingException, IOException, RestClientException{ 
//   		GenericUtil.validarCampoRequeridoBO(strToken, "tasm.campos.headerAutorizacion");

   		DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM);
//           UsuarioLogin usuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   		try {
   			// UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
   			return new ResponseEntity<>(
   					new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)), 
   							objIUsuariosBO.verificarCuenta(usuario)), 
   					HttpStatus.OK);
   			
   		} catch (BOException e) {
   			logger.error(e);
   			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
   		}  finally {
   			DBContextHolder.clear();
   		}
   	}
    
    @RequestMapping(value = "/organizaciones",method = RequestMethod.GET)  
   	public ResponseEntity<?> organizacione(
   			@RequestHeader(value = "Accept-Language", required = false) String strLanguage ) 
   	throws   BOException, JsonProcessingException, IOException, RestClientException{ 
//   		GenericUtil.validarCampoRequeridoBO(strToken, "tasm.campos.headerAutorizacion");

   		DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM);
//           UsuarioLogin usuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   		try {
   			// UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
   			return new ResponseEntity<>(
   					new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)), 
   							objIUsuariosBO.consultarOrganizaciones()), 
   					HttpStatus.OK);
   			
   		} catch (BOException e) {
   			logger.error(e);
   			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
   		}  finally {
   			DBContextHolder.clear();
   		}
   	}
     
	
 
	
}
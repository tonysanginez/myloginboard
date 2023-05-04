package com.tasm.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasm.bo.IAutenticacionBO;
import com.tasm.db.config.DBContextHolder;
import com.tasm.dto.response.ResponseOk;
import com.tasm.dto.response.ResponseUnauthorized;
import com.tasm.enums.AuthenticationScheme;
import com.tasm.enums.DBTypeEnum;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.UnauthorizedException;
import com.tasm.security.SecurityUtil;
import com.tasm.util.GenericUtil;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/autenticacion")
public class AutenticacionApi {
	
	private static final Logger logger = LogManager.getLogger(AutenticacionApi.class.getName());
	
	@Autowired
	private IAutenticacionBO objIAutenticacionBO;

	@RequestMapping(value = "/login", 
					method = RequestMethod.POST,
					produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> autenticacion​Login(@RequestHeader(value = "Accept-Language", required = false) String strLanguage,
												@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization) 
	throws BOException, UnauthorizedException {
		GenericUtil.validarCampoRequeridoBO(strAuthorization, "tasm.campos.headerAutorizacion");
		try {
			// UsuarioLogin objUsuarioLogin = (UsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM);
			
			String[] arrCrendencialesAuth = SecurityUtil.obtenerBasicAuth(strAuthorization, AuthenticationScheme.BASIC.toString());
			return new ResponseEntity<>(
					new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)), 
								   objIAutenticacionBO.userLogin(arrCrendencialesAuth[0], arrCrendencialesAuth[1])), 
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
	
}
package com.tasm.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasm.bo.IUtilBO;
import com.tasm.db.config.DBContextHolder;
import com.tasm.dto.response.ResponseError;
import com.tasm.dto.response.ResponseOk;
import com.tasm.dto.util.ValidacionNumeroTelefonicoDTO;
import com.tasm.enums.DBTypeEnum;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.GenericUtil;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/util")
public class UtilApi {

	private static final Logger logger = LogManager.getLogger(UtilApi.class.getName());

	@Autowired
	private IUtilBO objIUtilBO;
	
	
	@RequestMapping(value = "/validacion_numero_telefonico", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> validarNumeroTelefonico(@RequestHeader(value = "Accept-Language", required = false) String strLanguage,
													 HashMap<String, Object> requestBody) {
		try {
			DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM);

			String strNumeroTelefonico = requestBody.get("numeroTelefonico") != null ? (String) requestBody.get("numeroTelefonico") : null;
			String strPaisISO = requestBody.get("paisISO") != null ? (String) requestBody.get("paisISO") : null;
			String strTipoNumero = requestBody.get("tipo") != null ?  (String) requestBody.get("tipo") : null;

			Map<String, Object> mapResult = objIUtilBO.validarNumeroTelefonico(strNumeroTelefonico, strPaisISO, strTipoNumero.toUpperCase());
			
			ValidacionNumeroTelefonicoDTO objvalidNumeroTelefonico  = null;
			boolean booResultValidacion = false;
			String strMensajeRespuesta = "tasm.response.ok";
			
			if (mapResult != null) {
				for (Map.Entry<String, Object> entry : mapResult.entrySet()) {
					if ("OBJ_VALID_NUME_TELE".equalsIgnoreCase(entry.getKey())) {
						objvalidNumeroTelefonico = (ValidacionNumeroTelefonicoDTO) entry.getValue();
					}
					if ("RESULTADO_VALIDACION".equalsIgnoreCase(entry.getKey())) {
						booResultValidacion = (boolean) entry.getValue();
					}
					if ("MENSAJE_RESPUESTA".equalsIgnoreCase(entry.getKey())) {
						strMensajeRespuesta = (String) entry.getValue();
					}
				}
			}
			
			//
			if (booResultValidacion) {
				return new ResponseEntity<>(
						new ResponseOk(MensajesUtil.getMensaje(strMensajeRespuesta, MensajesUtil.validateSupportedLocale(strLanguage)), 
									   objvalidNumeroTelefonico), 
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResponseError(MensajesUtil.getMensaje(strMensajeRespuesta, MensajesUtil.validateSupportedLocale(strLanguage)), null, HttpStatus.BAD_REQUEST.value()), 
						HttpStatus.BAD_REQUEST);
			}

		} catch (BOException e) {
			logger.error(e);
			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
		} finally {
			DBContextHolder.clear();
		}
	}
	
	@RequestMapping(value = "/validar_identificacion", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> validaIdentificacion(@RequestHeader(value = "Accept-Language", required = false) String strLanguage,
												  @RequestParam(value = "codigoTipoIdentificacion", required = false) Short shoCodigoTipoIdentificacion,
												  @RequestParam(value = "numeroIdentificacion", required = false) String strNumeroIdentificacion,
												  @RequestParam(value = "codigoEmpresa", required = false) Short shoCodigoEmpresa) 
	throws BOException {
		GenericUtil.validarCampoRequeridoBO(shoCodigoTipoIdentificacion, "codigoTipoIdentificacion");
		GenericUtil.validarCampoRequeridoBO(strNumeroIdentificacion, "numeroIdentificacion");
		GenericUtil.validarCampoRequeridoBO(shoCodigoEmpresa, "codigoEmpresa");
		try {
			DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM);
			
			return new ResponseEntity<>(
					new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)), 
								   objIUtilBO.validarIdentificacion(strNumeroIdentificacion, shoCodigoTipoIdentificacion, shoCodigoEmpresa)), 
					HttpStatus.OK);

		} catch (BOException e) {
			logger.error(e);
			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
		} finally {
			DBContextHolder.clear();
		}
	}
	
}
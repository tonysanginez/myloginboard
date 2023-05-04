package com.tasm.bo.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasm.bo.IUtilBO;
import com.tasm.dao.GenTiposIdentificacionDAO;
import com.tasm.dto.util.ValidacionNumeroTelefonicoDTO;
import com.tasm.enums.TipoNumero;
import com.tasm.exceptions.BOException;
import com.tasm.helper.IdentificacionHelper;
import com.tasm.model.gen.GenCodTelefonicoXEmpresa;
import com.tasm.model.gen.GenTiposIdentificacion;
import com.tasm.model.gen.GenTiposIdentificacionCPK;
import com.tasm.util.NumeroTelefonicoUtil;

@Service
public class UtilBOImpl implements IUtilBO {
	
	@Autowired
	private GenTiposIdentificacionDAO objGenTiposIdentificacionDAO;

	@Override
	public Map<String, Object> validarNumeroTelefonico(String strNumeroTelefonico, String strPaisISO, String strTipoNumero) 
	throws BOException {

		// Valida campos obligatorios
		if (StringUtils.isBlank(strNumeroTelefonico)) {
			throw new BOException("tasm.warn.campoObligatorio", new Object[] { "numeroTelefonico" });
		}
		if (StringUtils.isBlank(strPaisISO)) {
			throw new BOException("tasm.warn.campoObligatorio", new Object[] { "paisISO" });
		}
		if (StringUtils.isBlank(strTipoNumero)) {
			throw new BOException("tasm.warn.campoObligatorio", new Object[] { "tipoNumero" });
		}
		
		// Valida tipo de número
		boolean booFound = false;
		for (TipoNumero filtro : TipoNumero.values()) {
			if (filtro.name().equals(strTipoNumero)) {
				booFound = true;
				break;
			}
		}
		if (!booFound) {
			throw new BOException("tasm.warn.tipoNumeroNoValido");
		}

		// Valida numero telefonico
		Map<String, Object> mapResult = NumeroTelefonicoUtil.validarNumeroTelefonico(strNumeroTelefonico, strPaisISO.toUpperCase(), new Locale("es"));
		if (mapResult == null || !Boolean.parseBoolean(mapResult.get("isValidNumber").toString())) {
			if (TipoNumero.MOBILE.getName().equalsIgnoreCase(strTipoNumero)) {
				throw new BOException("tasm.warn.telefonoCelularInvalido");
			} else if (TipoNumero.FIXED_LINE.getName().equalsIgnoreCase(strTipoNumero)) {
				throw new BOException("tasm.warn.telefonoConvencionalInvalido");
			} else {
				throw new BOException("tasm.warn.numeroTelefonicoInvalido");
			}
		} else if (mapResult != null && !Boolean.parseBoolean(mapResult.get("isValidNumberForRegion").toString())) {
			throw new BOException("tasm.warn.numeroTelefonicoInvalidoRegion");
		}
		
		String strNumberType = null;
		if (mapResult.get("numberType") != null) {
			strNumberType = mapResult.get("numberType").toString();
		} else {
			throw new BOException("tasm.warn.tipoNumeroRetorno");
		}
		
		// Prepara DTO a retornar
		ValidacionNumeroTelefonicoDTO objValidNumeroTelefonico = ValidacionNumeroTelefonicoDTO.builder()
				.codigoPais(mapResult.get("countryCode") != null ? mapResult.get("countryCode").toString() : null)
				.esValido(mapResult.get("isValidNumber") != null && Boolean.parseBoolean(mapResult.get("isValidNumber").toString()) ? "S" : "N")
				.formatoE164(mapResult.get("E164Format").toString())
				.formatoInternacional(mapResult.get("internationalFormat") != null ? mapResult.get("internationalFormat").toString() : null)
				.formatoNacional(mapResult.get("nationalFormat") != null ? mapResult.get("nationalFormat").toString() : null)
				.numeroNacional(mapResult.get("nationalNumber") != null ? mapResult.get("nationalNumber").toString() : null)
				.region(mapResult.get("region") != null ? mapResult.get("region").toString() : null)
				.tipo(mapResult.get("numberType") != null ? mapResult.get("numberType").toString() : null)
				.ubicacion(mapResult.get("location") != null ? mapResult.get("location").toString() : null)
				.zonaHoraria(mapResult.get("timeZones") != null ? mapResult.get("timeZones").toString() : null)
				.formatoNacionalSoloNumeros(mapResult.get("nationalFormat") != null ? mapResult.get("nationalFormat").toString().replace(" ", "").replace("(", "").replace(")", "").replace("-", "") : null)
				.operadora(mapResult.get("carrier") != null ? mapResult.get("carrier").toString() : null)
				.build();
		
		//
		boolean booResultValidacion = true;
		String strMensajeRespuesta = "tasm.response.ok";
		if (!strNumberType.equalsIgnoreCase(strTipoNumero)) {
			if (strTipoNumero.equalsIgnoreCase(TipoNumero.MOBILE.getName()) || strTipoNumero.equalsIgnoreCase(TipoNumero.FIXED_LINE.getName())) {
				return validarNumeroTelefonico(strNumeroTelefonico, strPaisISO, TipoNumero.FIXED_LINE_OR_MOBILE.getName());
			} else {
				booResultValidacion = false;
				strMensajeRespuesta = "tasm.warn.numeroTelefonoNoValidoConTipo";
			}
		}
		
		//
		Map<String, Object> mapResultReturn = new HashMap<String, Object>();
		mapResultReturn.put("OBJ_VALID_NUME_TELE", objValidNumeroTelefonico);
		mapResultReturn.put("RESULTADO_VALIDACION", booResultValidacion);
		mapResultReturn.put("MENSAJE_RESPUESTA", strMensajeRespuesta);
		return mapResultReturn;

	}
	
	@Override
	public ValidacionNumeroTelefonicoDTO obtenerTelefonoValidado(String strNumero, String strTipoNumero, Optional<GenCodTelefonicoXEmpresa> objCodTelePais) 
	throws BOException {

		String strCodigoTelefonico = (objCodTelePais.get().getDafPaises() != null && objCodTelePais.get().getDafPaises().getCodigoTelefonico() != null) ? objCodTelePais.get().getDafPaises().getCodigoTelefonico() : null;
		StringBuilder strE164 = new StringBuilder();
		String strNumeroTelefonico = strE164.append(strCodigoTelefonico).append(strNumero.trim()).toString().replaceAll("\\s", "");
		String strPaisISO = (objCodTelePais.get().getDafPaises() != null && objCodTelePais.get().getDafPaises().getCodigoISO() != null) ? objCodTelePais.get().getDafPaises().getCodigoISO() : null;

		Map<String, Object> mapResponseValiTele = validarNumeroTelefonico(strNumeroTelefonico, strPaisISO, strTipoNumero);
		boolean booEsValido = (boolean) mapResponseValiTele.get("RESULTADO_VALIDACION");
		ValidacionNumeroTelefonicoDTO objValiNumeTeleDTO = new ValidacionNumeroTelefonicoDTO();
		String strMensajeRespuesta = (String) mapResponseValiTele.get("MENSAJE_RESPUESTA");
		if (mapResponseValiTele != null) {
			if (booEsValido) {
				objValiNumeTeleDTO = (ValidacionNumeroTelefonicoDTO) mapResponseValiTele.get("OBJ_VALID_NUME_TELE");
			} else {
				throw new BOException(strMensajeRespuesta);
			}
		}
		return objValiNumeTeleDTO;
	}
	
	@Override
	public Map<String, Boolean> validarIdentificacion(String strNumeroIdentificacion, Short shoCodigoTipoIdentificacion, Short shoCodigoEmpresa) throws BOException {
		HashMap<String, Boolean> response = new HashMap<String, Boolean>();
		Optional<GenTiposIdentificacion> objTiposIdentiXEmpresa = objGenTiposIdentificacionDAO.find(new GenTiposIdentificacionCPK(shoCodigoTipoIdentificacion, shoCodigoEmpresa));
		if (!objTiposIdentiXEmpresa.isPresent()) {
			throw new BOException("tasm.warn.tipoIdentificacionEmpresaInvalido");
		}
		// Validar si el numero de identificacion es correcto
		response.put("esIdentificacionValida", IdentificacionHelper.identificacionValida(strNumeroIdentificacion, objTiposIdentiXEmpresa.get().getAlgoritmoIdentificacion()));
		return response;
	}

}
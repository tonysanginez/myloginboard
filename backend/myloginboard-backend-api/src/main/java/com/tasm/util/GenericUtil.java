package com.tasm.util;

import static com.google.common.io.BaseEncoding.base64;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tasm.dao.GenPaisesDAO;
import com.tasm.enums.TipoNumero;
import com.tasm.exceptions.BOException;
import com.tasm.model.gen.GenPaises;

public class GenericUtil {
	
    @Autowired
    private GenPaisesDAO objGenPaisesDAO; 
	
	private GenericUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Valida campo requerido con isBlank() y lanza excepcion dinamica de campo
	 * obligatorio.
	 * 
	 * @param strCampoRequerido
	 * @param strNombreCampo
	 * @return BOException
	 * @throws BOException
	 */
	public static void validarCampoRequeridoBO(String strCampoRequerido, String strNombreCampo) 
	throws BOException {
		if (StringUtils.isBlank(strCampoRequerido)) {
			throw new BOException("tasm.warn.campoObligatorio", new Object[] { strNombreCampo });
		}
	}
	
	/**
	 * Valida campo requerido con isEmpty()) y lanza excepcion dinamica de campo
	 * obligatorio.
	 * 
	 * @param Object objCampoRequerido
	 * @param String strNombreCampo
	 * @return BOException
	 * @throws BOException
	 */
	public static void validarCampoRequeridoBO(Object objCampoRequerido, String strNombreCampo) 
	throws BOException {
		if (ObjectUtils.isEmpty(objCampoRequerido)) {
			throw new BOException("tasm.warn.campoObligatorio", new Object[] { strNombreCampo });
		}
	}
	
	/**
	 * Valida campo requerido con isEmpty()) que sea mayor a cero y lanza excepcion dinamica de campo
	 * obligatorio.
	 * 
	 * @param Object objCampoRequerido
	 * @param String strNombreCampo
	 * @return BOException
	 * @throws BOException
	 */
	public static void validarRequeridoMayorACero(Object objCampoRequerido, String strNombreCampo) 
	throws BOException {
		if (ObjectUtils.isEmpty(objCampoRequerido)) {
			throw new BOException("tasm.warn.campoObligatorio", new Object[] { strNombreCampo });
		}
		if (objCampoRequerido instanceof String) {
			throw new BOException("tasm.warn.datoDebeSerEntero", new Object[] { strNombreCampo });
		}
		if (objCampoRequerido instanceof Integer) {
			if ((Integer)objCampoRequerido<=0) {
				throw new BOException("tasm.warn.campoMayorCero", new Object[] { strNombreCampo });
			}
		}
		if (objCampoRequerido instanceof Long) {
			if ((Long)objCampoRequerido<=0) {
				throw new BOException("tasm.warn.campoMayorCero", new Object[] { strNombreCampo });
			}
		}
		if (objCampoRequerido instanceof BigDecimal) {
			if ( ((BigDecimal) objCampoRequerido).compareTo(BigDecimal.ZERO)<=0) {
				throw new BOException("tasm.warn.campoMayorCero", new Object[] { strNombreCampo });
			}
		}
		if (objCampoRequerido instanceof Short) {
			if ((Short)objCampoRequerido<=0) {
				throw new BOException("tasm.warn.campoMayorCero", new Object[] { strNombreCampo });
			}
		}
	}
	
	/**
	 * Valida campo requerido con isEmpty() y cantidad maximo de caracteres de un string con length() 
	 * pasando como parametro tambien la cantidad maxima y lanza excepcion dinamica de campo
	 * obligatorio.
	 * 
	 * @param strCampoRequerido
	 * @param strNombreCampo
	 * @param cantidadMaxima
	 * @return BOException
	 * @throws BOException
	 */
	public static void validarCantidadCaracteresCampo(String strCampoRequerido, String strNombreCampo,int cantidadMaxima) 
	throws BOException {
		if (!ObjectUtils.isEmpty(strCampoRequerido) && strCampoRequerido.length()>cantidadMaxima) {
			throw new BOException("tasm.warn.campoCantidadMaximaCaracteres", new Object[] { strNombreCampo });
		}
	}
	
	/**
	 * Valida URL
	 * @param url
	 * @return
	 */
	public static boolean isValidURL(String url) {
	    try {
	        new URL(url).toURI();
	    } catch (MalformedURLException | URISyntaxException e) {
	        return false;
	    }
	    return true;
	}
	
	/**
	 *
	 * Decodifica Base64
	 * 
	 * @param stringBase64
	 * @return
	 * @throws BOException
	 */
	public static String decodificaBase64(String stringBase64) throws BOException {
		String decodeString = null;
		if (!StringUtils.isBlank(stringBase64)) {
			decodeString = new String(base64().decode(stringBase64), StandardCharsets.UTF_8);
		}
		return decodeString;
	}

	/**
	 *
	 * Decodifica Base64
	 * 
	 * @param stringBase64
	 * @return
	 * @throws BOException
	 */
	public static String encodificaBase64(String stringBase64) {
		String encodeString = null;
		if (!StringUtils.isBlank(stringBase64)) {
			encodeString = new String(base64().encode(stringBase64.getBytes(StandardCharsets.UTF_8)));
		}
		return encodeString;
	}
	
}
package com.tasm.security;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.util.Base64;

import com.tasm.enums.AuthenticationScheme;
import com.tasm.util.MensajesUtil;

public class SecurityUtil {

	// Loggers
	private static final Logger logger = LogManager.getLogger(SecurityUtil.class.getName());

	/**
	 * Metodo que transforma el Header de Autenticacion y retorna un arreglo con el usuario y la contraseña.
	 * Pos 0 = Usuario, Pos 1 = Contraseña
	 * 
	 * @param strAuthorization
	 * @return
	 */
	public static String[] obtenerBasicAuth(String strAuthorization, String strTipoAuth) {
		String[] values = null;
		if (strTipoAuth == AuthenticationScheme.BASIC.toString()) {
			try {
				String base64Credentials = strAuthorization.substring(AuthenticationScheme.BASIC.toString().length()).trim();
				
				String credentials = new String(Base64.decode(base64Credentials.getBytes()), Charset.forName("UTF-8"));
				values = credentials.split(":", 2);
				if (values.length == 2) {
					return values;
				}
			} catch (IOException e) {
				logger.error(e);
				throw new ClientErrorException(MensajesUtil.getMensaje("tasm.error.errorDecodeAuth", MensajesUtil.Locale), Status.UNAUTHORIZED, e);
			}
		}
		// TIPO DE AUTORIZACION NO SOPORTADA
		throw new ClientErrorException(MensajesUtil.getMensaje("tasm.warn.tipoAutorizacionNoSoportada", MensajesUtil.Locale), Status.UNAUTHORIZED);
	}

	/**
	 *
	 * Decodifica valor Base64
	 * 
	 * @return
	 */
	public static String decodificaBase64(String stringBase64) {
		String decodeString = null;
		try {
			if (!StringUtils.isBlank(stringBase64)) {
				decodeString = new String(Base64.decode(stringBase64.getBytes()), Charset.forName("UTF-8"));
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			throw new RuntimeException(MensajesUtil.getMensaje("tasm.error.errorDecodeAuth", MensajesUtil.Locale), e);
		}
		return decodeString;
	}

}
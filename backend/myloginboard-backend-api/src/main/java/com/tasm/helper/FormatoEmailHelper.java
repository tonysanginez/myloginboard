package com.tasm.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper de validacion de formatos de emails
 */
public class FormatoEmailHelper {

	private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	/**
	 * Valida si el formato de un email es correcto.
	 * 
	 * @author Ivan Marriott
	 * @param email
	 */
	public static boolean emailValido(String email) {
		if (email == null) {
			return false;
		}
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

	/**
	 * Extrae las direcciones de correo separados por el caracter punto y coma (;) y
	 * retorna un array con las direcciones de correo.
	 * 
	 * @param emailAddressList Cadena con direcciones de correo separadas por el
	 *                         caracter punto y coma.
	 * @return Retorna array con direcciones de correo.
	 */
	public static String[] extraerEmailAddresses(String emailAddressList) {
		return emailAddressList.replaceAll("(@.*?>?)\\s*[,;]", "$1<|>").replaceAll("<\\|>$", "").split("\\s*<\\|>\\s*");
	}
	
}
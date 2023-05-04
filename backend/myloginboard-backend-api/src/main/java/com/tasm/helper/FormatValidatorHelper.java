package com.tasm.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatValidatorHelper {

	/**
	 * Valida cantidad de digitos de una cadeana.
	 * 
	 * @param cadena
	 * @param cantidadDigitos
	 * @return
	 */
	public static boolean cantidadDigitosValida(String cadena, int cantidadDigitos) {
		if (cadena != null && !cadena.trim().equals("")) {
			final String VALID = ("^\\d{" + cantidadDigitos + "}");
			return cadena.matches(VALID);
		}
		return false;
	}

	/**
	 * Valida si solamente existen letras en una cadena.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean soloLetras(String input) {
		String regx = "^[\\p{L}]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

	/**
	 * Valida si solamente existen letras y espacios en blanco en una cadena.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean soloLetrasYEspacio(String input) {
		String regx = "^[\\p{L} ]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

	/**
	 * Valida si solamente existen numeros en una cadena.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean soloNumeros(String input) {
		String regx = "\\d+";
		return input.matches(regx);
	}

	/**
	 * Valida si solamente existen letras y numeros en una cadena.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean soloLetrasYNumeros(String input) {
		String regx = "^[a-zA-Z0-9]+$";
		return input.matches(regx);
	}

	/**
	 * Elimina acentos de una cadena.
	 * 
	 * @param input
	 * @return
	 */
	public static String eliminarAcentos(String input) {
		final String ORIGINAL = "ÁáÉéÍíÓóÚúÜü";
		final String REEMPLAZO = "AaEeIiOoUuUu";
		if (input == null) {
			return null;
		}
		char[] array = input.toCharArray();
		for (int indice = 0; indice < array.length; indice++) {
			int pos = ORIGINAL.indexOf(array[indice]);
			if (pos > -1) {
				array[indice] = REEMPLAZO.charAt(pos);
			}
		}
		return new String(array);
	}
	
}
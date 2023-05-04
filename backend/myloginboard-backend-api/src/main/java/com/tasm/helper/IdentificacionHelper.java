package com.tasm.helper;

import com.tasm.enums.AlgoritmosIdentificacion;
import com.tasm.helper.validadores.AlgoritmosIdentificacionEC;

/**
 * Helper de validacion de numeros de identificaciones aplicando el respectivo
 * algoritmo de validacion.
 *
 */
public class IdentificacionHelper {

	/**
	 * Evalua si una identificacion es correcta aplicando el respectivo algoritmo de
	 * validacion.
	 * 
	 * @param identificacion          Identificacion a validar.
	 * @param algoritmoIdentificacion Tipo de algoritmo a validar.
	 * @return
	 */
	public static boolean identificacionValida(String identificacion, AlgoritmosIdentificacion algoritmoIdentificacion) {
		return validaAlgoritmoIdentificacion(identificacion, algoritmoIdentificacion.getName());
	}

	/**
	 * Evalua si una identificacion es correcta aplicando el respectivo algoritmo de
	 * validacion.
	 * 
	 * @param identificacion          Identificacion a validar.
	 * @param algoritmoIdentificacion Tipo de algoritmo a validar.
	 * @return
	 */
	public static boolean identificacionValida(String identificacion, String algoritmoIdentificacion) {
		String strAlgoritmoFound = null;
		for (AlgoritmosIdentificacion alg : AlgoritmosIdentificacion.values()) {
			if (alg.getName().equals(algoritmoIdentificacion)) {
				strAlgoritmoFound = alg.getName();
				break;
			}
		}
		if (strAlgoritmoFound == null) {
			throw new RuntimeException("El algoritmo de identificacion no es valido.");
		}
		return validaAlgoritmoIdentificacion(identificacion, strAlgoritmoFound);
	}

	/**
	 * Ejecuta el algoritmo de validacion respectivo segun el tipo indicado.
	 * 
	 * @param identificacion
	 * @param algoritmoIdentificacion
	 * @return
	 */
	private static boolean validaAlgoritmoIdentificacion(String identificacion, String algoritmoIdentificacion) {
		if (algoritmoIdentificacion.equals(AlgoritmosIdentificacion.CEDULA_IDENTIDAD_EC.getName())) {
			return validaCedulaIdentidadEc(identificacion);
		} else if (algoritmoIdentificacion.equals(AlgoritmosIdentificacion.REGISTRO_UNICO_CONTRIBUYENTE_EC.getName())) {
			return validaRegistroUnicoContribuyenteEc(identificacion);
		} else if (algoritmoIdentificacion.equals(AlgoritmosIdentificacion.PASAPORTE.getName())) {
			return validaPasaporte(identificacion);
		}
		return false;
	}

	/**
	 * Validacion de Cedula de ciudadania de Ecuador - Se complementa Funcion
	 * validando que tenga 10 digitos
	 * 
	 * @param identificacion Identificacion a validar.
	 * @return
	 */
	private static boolean validaCedulaIdentidadEc(String identificacion) {
		if (identificacion != null && identificacion.trim().length() != 10) {
			return false;
		}
		return new AlgoritmosIdentificacionEC().validadorTipoIdentificacion(identificacion);
	}

	/**
	 * Validacion de Registro unico de contribuyente (RUC) de Ecuador. - Se
	 * complementa Funcion validando que tenga 13 digitos
	 * 
	 * @param identificacion Identificacion a validar.
	 * @return
	 */
	private static boolean validaRegistroUnicoContribuyenteEc(String identificacion) {
		if (identificacion != null && identificacion.trim().length() != 13) {
			return false;
		}
		return new AlgoritmosIdentificacionEC().validadorTipoIdentificacion(identificacion);
	}

	/**
	 * Validacion basica de un pasaporte
	 * 
	 * @param identificacion
	 * @return
	 */
	private static boolean validaPasaporte(String identificacion) {
		if (identificacion == null || identificacion.trim().equals("")) {
			return false;
		}
		// Valida que solo contenga letras y numeros
		if (!FormatValidatorHelper.soloLetrasYNumeros(identificacion)) {
			return false;
		}
		// Valida que tenga un maximo de 20 digitos
		if (identificacion.length() > 20) {
			return false;
		}
		return true;
	}

}
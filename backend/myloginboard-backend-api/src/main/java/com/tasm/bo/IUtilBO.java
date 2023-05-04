package com.tasm.bo;

import java.util.Map;
import java.util.Optional;

import com.tasm.dto.util.ValidacionNumeroTelefonicoDTO;
import com.tasm.exceptions.BOException;
import com.tasm.model.gen.GenCodTelefonicoXEmpresa;

public interface IUtilBO {
	
	/**
	 * Valida si un numero telefonico es correcto en un pais determinado.
	 * 
	 * @param strNumeroTelefonico
	 * @param strIsoPais
	 * @param strTipoNumero
	 * @return
	 * @throws BOException
	 */
	public Map<String, Object> validarNumeroTelefonico(String strNumeroTelefonico, String strIsoPais, String strTipoNumero) throws BOException;
	
	
	/**
	 * Obtener el objeto de teléfono validado
	 * 
	 * @param strNumero
	 * @param strTipoNumero
	 * @param objCodTelePais
	 * @return
	 * @throws BOException
	 */
	public ValidacionNumeroTelefonicoDTO obtenerTelefonoValidado(String strNumero, String strTipoNumero, Optional<GenCodTelefonicoXEmpresa> objCodTelePais) throws BOException;
	
	/**
	 * 
	 * Valida si un Tipo de identificación y Numero de identificación es valido 
	 * 
	 * @param strNumeroIdentificacion
	 * @param shoCodigoTipoIdentificacion
	 * @return
	 * @throws BOException
	 */
	public  Map<String, Boolean> validarIdentificacion(String strNumeroIdentificacion, Short shoCodigoTipoIdentificacion, Short shoCodigoEmpresa) throws BOException;
	
}
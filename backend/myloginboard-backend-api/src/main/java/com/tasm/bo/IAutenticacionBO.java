package com.tasm.bo;

import com.tasm.exceptions.BOException;
import com.tasm.exceptions.UnauthorizedException;
import com.tasm.security.UserLogin;
public interface IAutenticacionBO {
	
	/**
	 * Autenticacion de un usuario, mediante usuario y clave. Retorna tokens.
	 * @param strUsuario
	 * @param strClave
	 * @return
	 * @throws BOException
	 * @throws UnauthorizedException
	 */
	public UserLogin userLogin(String strUsuario, String strClave) throws BOException, UnauthorizedException;
	
	 

}
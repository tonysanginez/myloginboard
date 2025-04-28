package com.tasm.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasm.bo.IAutenticacionBO;
import com.tasm.dao.SecUsersDAO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.UnauthorizedException;
import com.tasm.model.sec.SecUsers;
import com.tasm.security.PayloadJWT;
import com.tasm.security.UserLogin;
import com.tasm.util.Encrypter;
import com.tasm.util.GenericUtil;
import com.tasm.util.JWTUtil;

@Service
public class AutenticacionBOImpl implements IAutenticacionBO {
	
	private static final Logger logger = LogManager.getLogger(AutenticacionBOImpl.class.getName());
	
	@Autowired
	private SecUsersDAO objSecUsersDAO;
	 

	@Override
	public UserLogin userLogin(String strUsuario, String strClave) 
	throws BOException, UnauthorizedException {
		
		GenericUtil.validarCampoRequeridoBO(strUsuario, "tasm.campos.usuario");
		GenericUtil.validarCampoRequeridoBO(strClave, "tasm.campos.clave");
		
		SecUsers objUsuario = objSecUsersDAO.validarUsuarioActivo(strUsuario);
		if (objUsuario == null) {
			throw new BOException("tasm.warn.usuarioNoExiste");
		} else if (!objUsuario.isStatus()) {
			throw new BOException("tasm.warn.cuentaInactiva");
		}
		
		try {
			System.out.println("Contraseña"+ Encrypter.encodePasswordHash("123"));
			if (!Encrypter.checkPassworHash(strClave, objUsuario.getPassword())) {
				throw new UnauthorizedException("tasm.warn.credencialesNoAutorizado");
			}
		} catch (Exception e) {
			logger.error(e);
			throw new BOException(e);
		}
		
		String strIdToken = null;
		try {
			
			long ttTiempoValido = 1000 * 60 * 60; // Asigna Tiempo de 1 hora
			strIdToken = JWTUtil.createJWT(strUsuario, 
										   ttTiempoValido, 
										   PayloadJWT.builder()
										    .idUser(objUsuario.getIdUser())
										    .username(objUsuario.getUsername())
											.build());
		} catch (Exception e) {
			logger.error(e);
			throw new BOException(e);
		}
		
		return UserLogin.builder()
				.idToken(strIdToken)
				.idUser(objUsuario.getIdUser())
			    .username(objUsuario.getUsername())
				.build();
	}
	 

}

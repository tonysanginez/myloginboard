package com.tasm.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasm.bo.IAutenticacionBO;
import com.tasm.dao.GenUsuariosDAO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.UnauthorizedException;
import com.tasm.model.gen.GenUsuarios;
import com.tasm.security.PayloadJWT;
import com.tasm.security.UsuarioLogin;
import com.tasm.util.Encrypter;
import com.tasm.util.GenericUtil;
import com.tasm.util.JWTUtil;

@Service
public class AutenticacionBOImpl implements IAutenticacionBO {
	
	private static final Logger logger = LogManager.getLogger(AutenticacionBOImpl.class.getName());
	
	@Autowired
	private GenUsuariosDAO objGenUsuariosDAO;
	 

	@Override
	public UsuarioLogin userLogin(String strUsuario, String strClave) 
	throws BOException, UnauthorizedException {
		
		GenericUtil.validarCampoRequeridoBO(strUsuario, "tasm.campos.usuario");
		GenericUtil.validarCampoRequeridoBO(strClave, "tasm.campos.clave");
		
		GenUsuarios objUsuario = objGenUsuariosDAO.validarUsuarioActivo(strUsuario);
		if (objUsuario == null) {
			throw new BOException("tasm.warn.usuarioNoExiste");
		} else if (!objUsuario.isEstado()) {
			throw new BOException("tasm.warn.cuentaInactiva");
		}
		
		try {
			if (!Encrypter.checkPassworHash(strClave, objUsuario.getContrasenia())) {
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
											.secuenciaUsuario(objUsuario.getSecuenciaUsuario())
											.codigoUsuario(objUsuario.getCodigoUsuario())
											.email(objUsuario.getEmail())
											.nombreUsuario(objUsuario.getNombreCompleto())
											.build());
		} catch (Exception e) {
			logger.error(e);
			throw new BOException(e);
		}
		
		return UsuarioLogin.builder()
				.idToken(strIdToken)
				.secuenciaUsuario(objUsuario.getSecuenciaUsuario())
				.codigoUsuario(objUsuario.getCodigoUsuario())
				.email(objUsuario.getEmail())
				.nombreUsuario(objUsuario.getNombreCompleto())
				.build();
	}
	 

}

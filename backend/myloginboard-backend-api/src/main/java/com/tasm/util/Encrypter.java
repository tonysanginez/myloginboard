package com.tasm.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encrypter {
    
    /**
     * Compruebe que una contraseña de texto sin formato coincida con un Hash previamente
     * @param strClave
     * @param strClaveHash
     * @return
     */
	public static boolean checkPassworHash(String strClave, String strClaveHash) {
    	try {
    		if (strClave!=null && strClaveHash!=null) {
		    	if (BCrypt.checkpw(strClave, strClaveHash)) {
		    		return true;
		    	}
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    	return false;
    }
    
    /**
     * Se genera una contraseña Hash usando el esquema bcrypt de OpenBSD
     * @param strClave
     * @return
     */
    public static String encodePasswordHash(String strClave) {
    	try {
    		if (strClave!=null) {
    			return BCrypt.hashpw(strClave, BCrypt.gensalt());
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    	return null;
    }
    
}
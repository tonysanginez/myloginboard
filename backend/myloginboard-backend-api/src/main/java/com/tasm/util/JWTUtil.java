package com.tasm.util;

import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.tasm.exceptions.BOException;
import com.tasm.security.PayloadJWT;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTUtil {
	
	private static final Logger logger = LogManager.getLogger(JWTUtil.class.getName());
	
	private static final String SECRET = "***J0It2022SuIt3Prd***";
	
	public static String createJWT(String strIdUser, long lngTtlMillis, PayloadJWT objPayloadJWT) 
	throws Exception {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId("$J-0-I-t$-Prd-2022$")
				   						   .setIssuedAt(now)
				   						   .setSubject("$$_JoIT-Suit3-ApplIC@ti0n.$2022")
										   .setIssuer(strIdUser)
										   .signWith(signatureAlgorithm, signingKey)
										   .claim("data", objPayloadJWT);

		// if it has been specified, let's add the expiration
		if (lngTtlMillis >= 0) {
			long expMillis = nowMillis + lngTtlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	
	public static boolean tokenValido(String strToken) {
		boolean valido;
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(strToken).getBody();
			valido = true;
		} catch (MalformedJwtException e) {
			logger.error(e);
			valido = false;
		} catch (ClaimJwtException e) {
			logger.error(e);
			valido = false;
		} catch (SignatureException e) {
			logger.error(e);
			valido = false;
		} catch (UnsupportedJwtException e) {
			logger.error(e);
			valido = false;
		} catch (Exception e) {
			logger.error(e);
			valido = false;
		}
		return valido;
	}
	
	public static boolean tokenExpirado(String strToken) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(strToken).getBody();
			return false;
		} catch (ExpiredJwtException e) {
			logger.error(e);
			return true;
		}
	}
	
	public static <T> T obtenerDataToken(String strToken, Class<T> classOfT) 
	throws BOException {
		Object dataNodo = null;		
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(strToken).getBody();
		try {
			dataNodo = claims.get("data");
			if (Objects.isNull(dataNodo)) {
				throw new BOException("tasm.warn.tokenNoData");
			}
		} catch (Exception e) {
			throw new BOException("tasm.warn.tokenNoData", e);
		}
		Gson gson = new Gson();
		String dataJson = null;
		try {
			dataJson = gson.toJson(dataNodo);
			if (Objects.isNull(dataJson)) {
				throw new BOException("tasm.warn.tokenNoJsonData");
			}
		} catch (Exception e) {
			throw new BOException("tasm.warn.tokenNoJsonData", e);
		}
		T objReturn = null;
		try {
			objReturn = gson.fromJson(dataJson, classOfT);
		} catch (Exception e) {
			throw new BOException("tasm.warn.tokenDesObj", e);
		}
		return objReturn;
	}
	
}
package com.tasm.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tasm.enums.AuthenticationScheme;
import com.tasm.exceptions.UnauthorizedException;
import com.tasm.util.JWTUtil;

/**
 * Clase para filtrar cualquier solicitud entrante
 *
 */
@Component
public class SecurityRequestFilter extends OncePerRequestFilter {

	private static final Logger logger = LogManager.getLogger(SecurityRequestFilter.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	throws ServletException, IOException {
		try {
			String[] arrServiciosSinSeguridad = { "/tasm/v1/health", "/tasm/v1/autenticacion/login", "/tasm/v1/usuario/verifica_cuenta" };
			UserLogin objUsuarioLogin = null;
			if (!(request.getRequestURI() != null && Arrays.stream(arrServiciosSinSeguridad).anyMatch(StringUtils.upperCase(request.getRequestURI())::equalsIgnoreCase))) {

				String strAutorization = request.getHeader(HttpHeaders.AUTHORIZATION);
				if (strAutorization == null || strAutorization.isEmpty()) {
					throw new UnauthorizedException("tasm.error.noTokenAuth");
				}
				String strToken = null;
				if (strAutorization == null || !strAutorization.startsWith(AuthenticationScheme.BEARER.toString() + " ")) {
					throw new UnauthorizedException("tasm.error.noTokenAuth");
				} else {
					strToken = strAutorization.substring(AuthenticationScheme.BEARER.toString().length()).trim();
				}
				if (strToken == null || strToken.isEmpty()) {
					throw new UnauthorizedException("tasm.error.noTokenAuth");
				}
				if (strToken != null && !strToken.isEmpty()) {
					if (!JWTUtil.tokenValido(strToken)) {
						throw new UnauthorizedException("tasm.error.tokenIncorrectoOExpiradoAuth");
					}
					if (JWTUtil.tokenExpirado(strToken)) {
						throw new UnauthorizedException("tasm.error.tokenIncorrectoOExpiradoAuth");
					}
				}
				UserLogin dataLogin = JWTUtil.obtenerDataToken(strToken, UserLogin.class);
				objUsuarioLogin = UserLogin.builder()
						.idToken(strToken)
						.idUser(dataLogin.getIdUser())
						.username(dataLogin.getUsername())
						.build();
			}
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(objUsuarioLogin, null, new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		} catch (UnauthorizedException e1) {
			logger.error("AutorizadorException: " + e1);
			response.setHeader("statusCodeAutorizadorException", String.valueOf(Status.UNAUTHORIZED.getStatusCode()));
			response.setHeader("msgAutorizadorException", e1.getMessage());
			response.setStatus(Status.UNAUTHORIZED.getStatusCode());
		} catch (Exception e) {
			logger.error("Exception SecurityRequestFilter: " + e);
			response.setHeader("statusCodeAutorizadorException", String.valueOf(Status.INTERNAL_SERVER_ERROR.getStatusCode()));
			response.setHeader("msgAutorizadorException", e.getMessage());
			response.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
		chain.doFilter(request, response);
	}

}
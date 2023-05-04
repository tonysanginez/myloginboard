package com.tasm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase con anotación EnableWebSecuritypara que sirve para habilitar el soporte
 * de seguridad web de Spring Security y proporcionar la integración Spring MVC
 * 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityRequestFilter securityRequestFilter;

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Deshabilitar Falsificación de solicitudes en sitios cruzados (CSRF -
		// Cross‐site Request Forgery)
		httpSecurity.csrf().disable().
		// Permitir a todos los usuarios acceder a las páginas que comienza con /
				authorizeRequests().antMatchers("/").permitAll().
				// Cualquier solicitud a la aplicación debe ser autenticado
				anyRequest().authenticated().and().
				// Un solo punto de entrada para autenticar
				exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).and().
				// No se creará ni utilizará ninguna sesión
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Agregar un filtro para validar los tokens de cada request
		httpSecurity.addFilterBefore(securityRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}

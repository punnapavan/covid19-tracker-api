package com.covid.model;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private String encryptionKey;
	private String jwtKey;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, String encryptionKey, String jwtKey) {
		super(authenticationManager);
		this.encryptionKey = encryptionKey;
		this.jwtKey = jwtKey;
	}
}

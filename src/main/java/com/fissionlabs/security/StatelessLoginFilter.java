package com.fissionlabs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fissionlabs.dto.LoginRequestDTO;
import com.fissionlabs.model.User;

/**
 * 
 * @author Karthik
 *
 */
public class StatelessLoginFilter extends
		AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;
	private final UserDetailsServiceImpl userDetailsService;

	public StatelessLoginFilter(String urlMapping,
			TokenAuthenticationService tokenAuthenticationService,
			UserDetailsServiceImpl userDetailsService,
			AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		final LoginRequestDTO loginDTO = new ObjectMapper().readValue(
				request.getInputStream(), LoginRequestDTO.class);
		final UsernamePasswordAuthenticationToken loginToken;

		if (loginDTO.getUsername() != null && loginDTO.getPassword() != null) {
			loginToken = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
			return getAuthenticationManager().authenticate(loginToken);
		}

		return new UsernamePasswordAuthenticationToken(null, null);

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {

		// Lookup the complete User object from the database and create an
		// Authentication for it
		final User authenticatedUser = userDetailsService
				.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(
				authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response,
				userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext()
				.setAuthentication(userAuthentication);

	}
}

package com.fissionlabs.config;



import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fissionlabs.security.StatelessAuthenticationFilter;
import com.fissionlabs.security.StatelessLoginFilter;
import com.fissionlabs.security.TokenAuthenticationService;
import com.fissionlabs.security.UserDetailsServiceImpl;

/**
 * Spring security configuration class.
 * 
 * @author Karthik
 * 
 */
@Configuration
@EnableScheduling
@EnableAsync
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class RootSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	RootSpringSecurityConfig() {
		super(true);
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.and()
				.anonymous()
				.and()
				.servletApi()
				.and()
				.headers()
				.cacheControl()
				.and().and()
				.authorizeRequests()
				.anyRequest()
				.hasAnyRole("USER","ADMIN")
				.and()
				.addFilterBefore(
						new StatelessLoginFilter("/api/v1/auth/sign-in",
								tokenAuthenticationService, userDetailsService,
								authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(
						new StatelessAuthenticationFilter(
								tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement().sessionCreationPolicy(
				SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers( "/googlesignin");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(
				passwordEncoder);
		auth.eraseCredentials(false);

	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
}

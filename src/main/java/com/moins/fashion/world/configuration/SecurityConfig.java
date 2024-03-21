package com.moins.fashion.world.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.moins.fashion.world.security.JwtAuthenticationEntryPoint;
import com.moins.fashion.world.security.JwtAuthenticationFilter;
import com.moins.fashion.world.service.CustomAdminDetailService;
import com.moins.fashion.world.service.CustomCustomerDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	@Qualifier("adminDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("customerDetailsService")
	private UserDetailsService detailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String[] AUTH_WHITELIST = { "/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs.yaml/",
			"/swagger-ui/**", "/swagger-ui.html" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST).permitAll()
						.requestMatchers("/fashion/**", "/customerManager/**").permitAll()
						.requestMatchers("/dresses/**", "/customer/**", "/rent/**").authenticated().anyRequest()
						.authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
//	private AuthenticationFilter authenticationFilter() {
//        return new AuthenticationFilter(filter, authenticationFilter);
//    }

	@Bean(name = "adminManager")
	public AuthenticationManager adminAuthenticationManager(CustomAdminDetailService adminUserDetailsService,
			PasswordEncoder passwordEncoder) {
		return authenticationManager(adminUserDetailsService, passwordEncoder);
	}

	@Bean(name = "customerManager")
	@Primary
	public AuthenticationManager customerAuthenticationManager(CustomCustomerDetailsService customerUserDetailsService,
			PasswordEncoder passwordEncoder) {
		return authenticationManager(customerUserDetailsService, passwordEncoder);
	}

	private AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		List<AuthenticationProvider> providers = Arrays
				.asList(authenticationProvider(userDetailsService, passwordEncoder));
		return new ProviderManager(providers);
	}

	private AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {

		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setDefaultRolePrefix("");
		return expressionHandler;
	}

}

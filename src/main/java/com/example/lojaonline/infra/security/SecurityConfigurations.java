package com.example.lojaonline.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter filter;
	
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/cars").authenticated()
						.requestMatchers(HttpMethod.POST, "/cars/images").authenticated()
						.requestMatchers(HttpMethod.POST, "/cars/optionals").authenticated()
						.requestMatchers(HttpMethod.PUT, "/cars/{id}").authenticated()
						.requestMatchers(HttpMethod.PUT, "/cars/update-image").authenticated()
						.requestMatchers(HttpMethod.PUT, "/cars/update/{id}").authenticated()
						.requestMatchers(HttpMethod.DELETE, "/cars/{id}").authenticated()
						.requestMatchers(HttpMethod.DELETE, "/cars/optionals/delete/{id}").authenticated()
						.requestMatchers(HttpMethod.GET, "/cars").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/solds").authenticated()
						.requestMatchers(HttpMethod.GET, "/cars/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/images/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/optionals/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/search").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

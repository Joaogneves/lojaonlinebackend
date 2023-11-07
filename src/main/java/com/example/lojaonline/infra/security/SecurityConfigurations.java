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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


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
						.requestMatchers(HttpMethod.OPTIONS, "/cars/**").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/users/**").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/{id}").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/solds").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/solds/**").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/optionals/**").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/update-image").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/update/{id}").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/{id}").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/optionals/delete/{id}").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/users/{cpf}").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/auth/register").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/users").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/users/newpassword/{id}").permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/cars/images/**").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/users").authenticated()					
						.requestMatchers(HttpMethod.GET, "/users/{cpf}").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/cars/**").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/cars/images/**").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/cars/optionals/**").hasRole("USER")
						.requestMatchers(HttpMethod.PUT, "/cars/**").hasRole("USER")
						.requestMatchers(HttpMethod.PUT, "/cars/update-image").hasRole("USER")
						.requestMatchers(HttpMethod.PUT, "/cars/update/{id}").hasRole("USER")
						.requestMatchers(HttpMethod.PUT, "/users/newpassword/{id}").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/cars/**").hasRole("USER")
						.requestMatchers(HttpMethod.DELETE, "/cars/{id}").hasRole("USER")
						.requestMatchers(HttpMethod.DELETE, "/cars/optionals/delete/{id}").hasRole("USER")
						.requestMatchers(HttpMethod.GET, "/cars").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/images/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/optionals/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/cars/solds").hasRole("USER")
						.requestMatchers(HttpMethod.GET, "/cars/solds/{id}").hasRole("USER")
						.requestMatchers(HttpMethod.GET, "/users**").permitAll()
						.anyRequest().hasRole("USER"))
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
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        //config.setAllowedOrigins(Arrays.asList("*"));
        //config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        //config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        //config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
	
}

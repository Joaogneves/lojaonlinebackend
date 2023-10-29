package com.example.lojaonline.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.lojaonline.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService service;
	@Autowired
	private UserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = this.recoveryToken(request);
		if(token != null) {
			var subject  =service.validateToken(token);
			UserDetails user = repository.findByCpf(subject);
			
			var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		} 
		filterChain.doFilter(request, response);
	}
	
	private String recoveryToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null ) return null;
		return authHeader.replace("Bearer ", "");
	}
	
}

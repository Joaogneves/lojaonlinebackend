package com.example.lojaonline.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.lojaonline.entity.user.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		try {
			Algorithm alg = Algorithm.HMAC256(secret);
			String token = JWT.create().withIssuer("auth-api")
					.withSubject(user.getCpf())
					.withExpiresAt(generateExpDate())
					.sign(alg);
			return token;
		} catch(JWTCreationException ex) {
			throw new RuntimeException("ERROR! Error while generation token", ex);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm alg = Algorithm.HMAC256(secret);
			return JWT.require(alg)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch(JWTVerificationException ex) {
			return "";
		}
	}
	
	private Instant generateExpDate() { 
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}


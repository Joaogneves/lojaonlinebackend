package com.example.lojaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.entity.user.AuthenticatorDto;
import com.example.lojaonline.entity.user.LoginResponseDto;
import com.example.lojaonline.entity.user.User;
import com.example.lojaonline.infra.security.TokenService;
import com.example.lojaonline.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticatorController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticatorDto dto) {
		var userCpfPassword = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.password());
		var auth = this.manager.authenticate(userCpfPassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Valid User user) {
		if(repository.findByCpf(user.getCpf()) != null) return ResponseEntity.badRequest().build();
		String encriptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encriptedPassword);
		repository.save(user);
		return ResponseEntity.ok().build();
	}

}

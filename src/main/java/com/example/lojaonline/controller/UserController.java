package com.example.lojaonline.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.entity.user.User;
import com.example.lojaonline.entity.user.UserDto;
import com.example.lojaonline.entity.user.UserPasswordDto;
import com.example.lojaonline.service.UserService;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@GetMapping
	public List<User> getAllUsers() {
		return service.getAllUsers();
		
	}
	
	@GetMapping(value = "{cpf}")
	public UserDto findByCpf(@PathVariable String cpf) {
		return service.findByCpf(cpf);
	}
	
	@PutMapping(value = "newpassword/{id}")
	public ResponseEntity<UserPasswordDto> newPassword(@PathVariable UUID id, @RequestBody UserPasswordDto dto) {
		service.setNewPassword(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	
}

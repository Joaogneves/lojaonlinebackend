package com.example.lojaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.entity.user.User;
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
}

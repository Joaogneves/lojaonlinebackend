package com.example.lojaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lojaonline.entity.user.User;
import com.example.lojaonline.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> getAllUsers() {
		return repository.findAll();
	}
}

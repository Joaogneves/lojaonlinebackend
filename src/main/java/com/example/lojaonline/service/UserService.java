package com.example.lojaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lojaonline.entity.user.User;
import com.example.lojaonline.entity.user.UserDto;
import com.example.lojaonline.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> getAllUsers() {
		return repository.findAll();
	}
	
	public UserDto findByCpf(String cpf) {
		User u = (User) repository.findByCpf(cpf);
		UserDto dto = new UserDto(u.getFirstName(), u.getLastName());
		return dto;
	}
}

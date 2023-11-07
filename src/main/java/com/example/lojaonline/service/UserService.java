package com.example.lojaonline.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lojaonline.entity.user.User;
import com.example.lojaonline.entity.user.UserDto;
import com.example.lojaonline.entity.user.UserPasswordDto;
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
		UserDto dto = new UserDto(u.getId(), u.getFirstName(), u.getLastName(), u.getRole().toString());
		return dto;
	}

	public void isActiveSet(UUID id) {
		User u = repository.findById(id).orElseThrow();
		if (u.getIsInative() == true) {
			u.activeUser();
		} else {
			u.inativeUser();
		}
		repository.save(u);
	}

	public void delete(UUID id) {
		User u = repository.findById(id).orElseThrow();
		if (u.getIsDeleted() != true) {
			u.setIsDeleted(true);
		}
		repository.save(u);
	}
	
	public void setNewPassword(UUID id, UserPasswordDto dto) {
		User u = repository.findById(id).orElseThrow();
		String encriptedPassword = new BCryptPasswordEncoder().encode(dto.password());
		u.setPassword(encriptedPassword);
		repository.save(u);
	}
}

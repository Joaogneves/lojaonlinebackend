package com.example.lojaonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.lojaonline.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		return repository.findByCpf(cpf);
	}

}

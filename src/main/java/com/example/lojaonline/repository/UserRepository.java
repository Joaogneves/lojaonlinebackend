package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.lojaonline.entity.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	UserDetails findByCpf(String cpf);
}

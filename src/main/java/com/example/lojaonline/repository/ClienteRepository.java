package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lojaonline.entity.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{

}

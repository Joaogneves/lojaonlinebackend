package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.entity.cliente.address.ClienteAddress;

public interface ClienteAddressRepositoryPk extends JpaRepository<ClienteAddress, UUID> {

}

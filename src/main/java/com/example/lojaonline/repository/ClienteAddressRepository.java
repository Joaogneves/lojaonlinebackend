package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.entity.cliente.address.Address;

public interface ClienteAddressRepository extends JpaRepository<Address, UUID> {

}

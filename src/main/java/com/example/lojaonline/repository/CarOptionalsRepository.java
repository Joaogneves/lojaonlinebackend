package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.entity.CarOptionals;


public interface CarOptionalsRepository extends JpaRepository<CarOptionals, UUID> {

}

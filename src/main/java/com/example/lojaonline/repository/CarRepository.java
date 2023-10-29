package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.entity.car.Car;


public interface CarRepository extends JpaRepository<Car, UUID> {	
}

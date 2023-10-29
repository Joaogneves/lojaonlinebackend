package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.entity.car.CarOptionals;


public interface CarOptionalsRepository extends JpaRepository<CarOptionals, UUID> {

}

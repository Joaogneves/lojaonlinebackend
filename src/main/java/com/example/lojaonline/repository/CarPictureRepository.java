package com.example.lojaonline.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.entity.car.CarPicture;


public interface CarPictureRepository extends JpaRepository<CarPicture, UUID> {
}

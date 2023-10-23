package com.example.lojaonline.entity;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_car_picture")
public class CarPicture {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String imgUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
	private Car car;
	
	public CarPicture() {}


	public CarPicture(UUID id, String imgUrl, Car car) {
		this.id = id;
		this.imgUrl = imgUrl;
		this.car = car;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarPicture other = (CarPicture) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}


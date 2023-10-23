package com.example.lojaonline.entity.dto;

import java.util.UUID;

public class CarCard {
	private UUID id;
	private String carBrand;
	private String name;
	private String description;
	private Double price;
	private String picture;
	private Integer carYear;
	private String carColor;

	public CarCard() {
	}

	public CarCard(UUID id, String carBrand, String name, String description, Double price, String picture,
			Integer carYear, String carColor) {
		this.id = id;
		this.carBrand = carBrand;
		this.name = name;
		this.description = description;
		this.price = price;
		this.picture = picture;
		this.carYear = carYear;
		this.carColor = carColor;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getCarYear() {
		return carYear;
	}

	public void setCarYear(Integer carYear) {
		this.carYear = carYear;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
}


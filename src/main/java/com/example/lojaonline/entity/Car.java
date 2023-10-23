package com.example.lojaonline.entity;

import java.util.List;
import java.util.UUID;

import com.example.lojaonline.entity.enums.CarColor;
import com.example.lojaonline.entity.enums.FuelType;
import com.example.lojaonline.entity.enums.TransmissionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_car")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id = UUID.randomUUID();
	
	@Column(nullable = false)
	private String carBrand;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private Integer carYear;
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private Boolean isSold = false;
	@Column(nullable = false)
	private Boolean isDeleted = false;
	@Column(nullable = true)
	private String pictureUrl;
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private FuelType fuel;
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private TransmissionType transmissionType;
	@Enumerated(EnumType.STRING)
    @Column(nullable = true)
	private CarColor carColor;
	@Column(nullable = true)
	private Integer km;
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true)
	List<CarPicture> pictures;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true)
	private CarOptionals optionals;
	
	
	
	public Car(UUID id,String carBrand, String name, String description, Integer carYear, Double price, Boolean isSold,
			Boolean isDeleted, String pictureUrl, FuelType fuel, TransmissionType transmissionType, CarColor carColor,
			Integer km, List<CarPicture> pictures, CarOptionals optionals) {
		super();
		this.id = id;
		this.carBrand = carBrand;
		this.name = name;
		this.description = description;
		this.carYear = carYear;
		this.price = price;
		this.isSold = isSold;
		this.isDeleted = isDeleted;
		this.pictureUrl = pictureUrl;
		this.fuel = fuel;
		this.transmissionType = transmissionType;
		this.carColor = carColor;
		this.km = km;
		this.pictures = pictures;
		this.optionals = optionals;
	}

	public Car() {
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
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
	public Integer getCarYear() {
		return carYear;
	}
	public void setCarYear(Integer carYear) {
		this.carYear = carYear;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Boolean getIsSold() {
		return isSold;
	}
	public void setIsSold(Boolean isSold) {
		this.isSold = isSold;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<CarPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<CarPicture> pictures) {
		this.pictures = pictures;
	}
	
	public void sellCar() {
		this.isSold = true;
	}

	public String getPicture() {
		return pictureUrl;
	}

	public void setPicture(String picture) {
		this.pictureUrl = picture;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public FuelType getFuel() {
		return fuel;
	}

	public void setFuel(FuelType fuel) {
		this.fuel = fuel;
	}

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}

	public CarColor getCarColor() {
		return carColor;
	}

	public void setCarColor(CarColor carColor) {
		this.carColor = carColor;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public CarOptionals getOptionals() {
		return optionals;
	}

	public void setOptionals(CarOptionals optionals) {
		this.optionals = optionals;
	}
	
	
}

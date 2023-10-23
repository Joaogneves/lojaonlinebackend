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
@Table(name = "tb_optionals")
public class CarOptionals {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private Boolean electricWindow;
	private Boolean eletricLocks;
	private Boolean airConditioning;
	private Boolean hidraulicSteering;
	private Boolean airbags;
	private Boolean multmedia;
	private Boolean alarm;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
	private Car car;
	
	
	
	public CarOptionals() {
	}

	public CarOptionals(UUID id, Boolean electricWindow, Boolean eletricLocks, Boolean airConditioning,
			Boolean hidraulicSteering, Boolean airbags, Boolean multmedia, Boolean alarm, Car car) {
		super();
		this.id = id;
		this.electricWindow = electricWindow;
		this.eletricLocks = eletricLocks;
		this.airConditioning = airConditioning;
		this.hidraulicSteering = hidraulicSteering;
		this.airbags = airbags;
		this.multmedia = multmedia;
		this.alarm = alarm;
		this.car = car;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Boolean getElectricWindow() {
		return electricWindow;
	}
	public void setElectricWindow(Boolean electricWindow) {
		this.electricWindow = electricWindow;
	}
	public Boolean getEletricLocks() {
		return eletricLocks;
	}
	public void setEletricLocks(Boolean eletricLocks) {
		this.eletricLocks = eletricLocks;
	}
	public Boolean getAirConditioning() {
		return airConditioning;
	}
	public void setAirConditioning(Boolean airConditioning) {
		this.airConditioning = airConditioning;
	}
	public Boolean getHidraulicSteering() {
		return hidraulicSteering;
	}
	public void setHidraulicSteering(Boolean hidraulicSteering) {
		this.hidraulicSteering = hidraulicSteering;
	}
	public Boolean getAirbags() {
		return airbags;
	}
	public void setAirbags(Boolean airbags) {
		this.airbags = airbags;
	}
	public Boolean getMultmedia() {
		return multmedia;
	}
	public void setMultmedia(Boolean multmedia) {
		this.multmedia = multmedia;
	}
	public Boolean getAlarm() {
		return alarm;
	}
	public void setAlarm(Boolean alarm) {
		this.alarm = alarm;
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
		CarOptionals other = (CarOptionals) obj;
		return Objects.equals(id, other.id);
	}
	
	//tostring
	
	@Override
	public String toString() {
		return "CarOptionals [id=" + id + ", electricWindow=" + electricWindow + ", eletricLocks=" + eletricLocks
				+ ", airConditioning=" + airConditioning + ", hidraulicSteering=" + hidraulicSteering + ", airbags="
				+ airbags + ", multmedia=" + multmedia + ", alarm=" + alarm + ", car=" + car + "]";
	}
	
}

package com.example.lojaonline.entity.sale;

import java.util.Objects;
import java.util.UUID;

import com.example.lojaonline.entity.car.Car;
import com.example.lojaonline.entity.cliente.Cliente;
import com.example.lojaonline.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@OneToOne
	private Car car;
	@ManyToOne
	private User seller;
	
	@OneToOne
	private Cliente cliente;
	
	public Sale() {}
	
	public Sale(Car car, User seller, Cliente cliente) {
		this.car = car;
		this.seller = seller;
		this.cliente = cliente;
	}

	public Sale(UUID id, Car car, User seller, Cliente cliente) {
		this.id = id;
		this.car = car;
		this.seller = seller;
		this.cliente = cliente;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Sale other = (Sale) obj;
		return Objects.equals(id, other.id);
	}	
}

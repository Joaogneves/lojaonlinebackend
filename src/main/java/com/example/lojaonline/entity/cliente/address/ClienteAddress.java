package com.example.lojaonline.entity.cliente.address;

import java.util.Objects;
import java.util.UUID;

import com.example.lojaonline.entity.cliente.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente_address_pk")
public class ClienteAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id = UUID.randomUUID();;
	
	@OneToOne
	private Cliente cliente;
	
	@OneToOne
	private Address address;
	
	public ClienteAddress() {}
	
	public ClienteAddress(Cliente cliente, Address address) {
		this.cliente = cliente;
		this.address = address;
	}

	public ClienteAddress(UUID id, Cliente cliente, Address address) {
		this.id = id;
		this.cliente = cliente;
		this.address = address;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, cliente, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteAddress other = (ClienteAddress) obj;
		return Objects.equals(address, other.address) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(id, other.id);
	}
}

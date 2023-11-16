package com.example.lojaonline.entity.cliente.address;

import java.util.Objects;
import java.util.UUID;

import com.example.lojaonline.entity.cliente.address.enums.UF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id = UUID.randomUUID();
	@Column(nullable = false)
	private String cep;
	@Column(nullable = false)
	private String streetName;
	@Column(nullable = false)
	private String streetNumber;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String neighborhood;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UF uf;
	@Column(nullable = true)
	private String complement;
	
	@OneToOne
	private ClienteAddress clienteAdress;
	
	public Address() {}

	public Address(UUID id, String cep, String streetName, String streetNumber, String city, String neighborhood, UF uf,
			String complement, ClienteAddress clienteAdress) {
		this.id = id;
		this.cep = cep;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.neighborhood = neighborhood;
		this.uf = uf;
		this.complement = complement;
		this.clienteAdress = clienteAdress;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public ClienteAddress getClienteAdress() {
		return clienteAdress;
	}

	public void setClienteAdress(ClienteAddress clienteAdress) {
		this.clienteAdress = clienteAdress;
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
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
}

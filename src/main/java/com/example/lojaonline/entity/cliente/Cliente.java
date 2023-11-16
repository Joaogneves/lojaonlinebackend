package com.example.lojaonline.entity.cliente;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.example.lojaonline.entity.cliente.address.ClienteAddress;
import com.example.lojaonline.entity.cliente.enums.Gender;
import com.example.lojaonline.entity.cliente.enums.MaritalStatus;

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
@Table(name = "tb_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id = UUID.randomUUID();
	
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false, unique = true)
	private String rg;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false, unique = true)
	private String cnh;
	@Column(nullable = false)
	private Date birthdate; 
	@Column(nullable = false, unique = true)
	private String whatsapp;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String motherName;
	@Column(nullable = false)
	private String fatherName;
	@Column(nullable = false)
	private String citizenship;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MaritalStatus maritalStatus;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	@Column(nullable = false)
	private Boolean isServed;
	
	@OneToOne
	private ClienteAddress clienteAddres;

	public Cliente() {}
	
	public Cliente(UUID id, String fullName, String rg, String cpf, String cnh, Date birthdate, String whatsapp,
			String email, String motherName, String fatherName, String citizenship, MaritalStatus maritalStatus,
			Gender gender, ClienteAddress clienteAddres) {
		this.id = id;
		this.fullName = fullName;
		this.rg = rg;
		this.cpf = cpf;
		this.cnh = cnh;
		this.birthdate = birthdate;
		this.whatsapp = whatsapp;
		this.email = email;
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.citizenship = citizenship;
		this.maritalStatus = maritalStatus;
		this.gender = gender;
		this.isServed = false;
		this.clienteAddres = clienteAddres;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Boolean getIsServed() {
		return isServed;
	}

	public void setIsServed(Boolean isServed) {
		this.isServed = isServed;
	}

	public ClienteAddress getClienteAddres() {
		return clienteAddres;
	}

	public void setClienteAddres(ClienteAddress clienteAddres) {
		this.clienteAddres = clienteAddres;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
}

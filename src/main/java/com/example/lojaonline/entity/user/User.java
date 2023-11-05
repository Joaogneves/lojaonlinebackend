package com.example.lojaonline.entity.user;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.lojaonline.entity.sale.Sale;
import com.example.lojaonline.entity.user.enums.UserRole;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "seller", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Sale> sale;
	
	public User() {}

	
	
	public User(UUID id, String firstName, String lastName, String cpf, String password, UserRole role,
			List<Sale> sale) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.password = password;
		this.role = role;
		this.sale = sale;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public UserRole getRole() {
		return role;
	}



	public void setRole(UserRole role) {
		this.role = role;
	}



	public List<Sale> getSale() {
		return sale;
	}

	public void setSale(List<Sale> sale) {
		this.sale = sale;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return this.firstName + " " +this.lastName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

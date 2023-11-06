package com.example.lojaonline.entity.user;

//import org.hibernate.validator.constraints.br.CPF;

import com.example.lojaonline.entity.user.enums.UserRole;

public record RegisterDto(String firstName, String lastName, String cpf, String password, UserRole userType) {

}

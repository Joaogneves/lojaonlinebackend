package com.example.lojaonline.entity.user;

import java.util.UUID;

public record UserDto(UUID id,String firstName, String lastName, String role) {

}

package com.ecommerce.authuser.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Data
public class UserDto {

    @NotBlank
    private UUID userId;
    @NotBlank
    @Size(min = 4, max = 11)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @CPF
    private String cpf;
}

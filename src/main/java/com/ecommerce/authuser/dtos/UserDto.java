package com.ecommerce.authuser.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

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

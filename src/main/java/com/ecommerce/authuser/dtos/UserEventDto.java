package com.ecommerce.authuser.dtos;

import com.ecommerce.authuser.enums.ActionType;
import lombok.Data;

import java.util.UUID;

@Data
public class UserEventDto {

    private UUID userId;
    private String username;
    private String email;
    private String cpf;
    private ActionType actionType;

}

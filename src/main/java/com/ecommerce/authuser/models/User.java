package com.ecommerce.authuser.models;

import com.ecommerce.authuser.dtos.UserEventDto;
import com.ecommerce.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserEventDto convertToUserEventDto() {
        var userEventDto = new UserEventDto();
        BeanUtils.copyProperties(this, userEventDto);
        return userEventDto;
    }
}

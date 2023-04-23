package com.ecommerce.authuser.repositories;

import com.ecommerce.authuser.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

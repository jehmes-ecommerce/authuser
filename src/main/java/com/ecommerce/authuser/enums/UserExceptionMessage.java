package com.ecommerce.authuser.enums;

public enum UserExceptionMessage {
    USERNAME_ALREADY_TAKEN("Username already taken"),
    EMAIL_ALREADY_TAKEN("Email already taken");

    private final String message;

    UserExceptionMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

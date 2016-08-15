package com.ilyamur.topaz.datalayer.spring.mybatis.service.exception;

public class LoginExistsException extends Exception {

    public static final String MESSAGE = "Login '%s' already exists in database.";

    private String message;

    public LoginExistsException(String email) {
        this.message = String.format(MESSAGE, email);
    }

    public LoginExistsException(String email, Throwable cause) {
        this(email);
        initCause(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }
}

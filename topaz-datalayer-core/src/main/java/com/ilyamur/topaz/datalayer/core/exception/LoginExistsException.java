package com.ilyamur.topaz.datalayer.core.exception;

// todo - remove?
public class LoginExistsException extends RuntimeException {

    public static final String MESSAGE = "Login '%s' already exists in database.";

    private String message;

    public LoginExistsException(String login) {
        this.message = String.format(MESSAGE, login);
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

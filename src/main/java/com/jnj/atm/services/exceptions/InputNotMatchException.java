package com.jnj.atm.services.exceptions;


public class InputNotMatchException extends Exception {
    public InputNotMatchException(String message) {
        super(message);
    }

    public InputNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

}

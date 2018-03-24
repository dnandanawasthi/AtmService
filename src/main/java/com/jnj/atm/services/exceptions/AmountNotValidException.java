package com.jnj.atm.services.exceptions;


public class AmountNotValidException extends Exception {
    public AmountNotValidException(String message) {
        super(message);
    }

    public AmountNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

}

package com.jnj.atm.services.exceptions;

public class AtmLowBalanceException extends Exception {
    public AtmLowBalanceException(String message) {
        super(message);
    }

    public AtmLowBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

}

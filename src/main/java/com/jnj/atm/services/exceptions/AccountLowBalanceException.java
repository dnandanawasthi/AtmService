package com.jnj.atm.services.exceptions;

public class AccountLowBalanceException extends Exception {
    public AccountLowBalanceException(String message) {
        super(message);
    }

    public AccountLowBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

}

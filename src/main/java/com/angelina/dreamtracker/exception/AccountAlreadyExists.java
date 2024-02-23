package com.angelina.dreamtracker.exception;

public class AccountAlreadyExists extends Exception {
    public AccountAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}

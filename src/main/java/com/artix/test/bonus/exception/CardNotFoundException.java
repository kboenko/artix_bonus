package com.artix.test.bonus.exception;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String message) {
        super(message);
    }
}

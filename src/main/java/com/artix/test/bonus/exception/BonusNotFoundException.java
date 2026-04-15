package com.artix.test.bonus.exception;

public class BonusNotFoundException extends RuntimeException {
    public BonusNotFoundException(String message) {
        super(message);
    }
}

package com.artix.test.bonus.exception;

public class NotEnoughBonusException extends RuntimeException{
    public NotEnoughBonusException(String message) {
        super(message);
    }
}

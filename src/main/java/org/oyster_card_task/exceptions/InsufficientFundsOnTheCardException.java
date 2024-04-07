package org.oyster_card_task.exceptions;

public class InsufficientFundsOnTheCardException extends RuntimeException {
    public InsufficientFundsOnTheCardException(String s) {
        super(s);
    }
}

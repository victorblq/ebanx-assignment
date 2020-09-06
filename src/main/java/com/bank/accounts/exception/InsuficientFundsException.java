package com.bank.accounts.exception;

public class InsuficientFundsException extends RuntimeException {
    public InsuficientFundsException(String message) {
        super(message);
    }
}

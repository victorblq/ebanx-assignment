package com.bank.accounts.exception;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException {

    private String field;
    public BadRequestException(String message, String field){
        super(message);
        this.field = field;
    }
}

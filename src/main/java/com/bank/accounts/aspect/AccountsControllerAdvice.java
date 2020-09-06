package com.bank.accounts.aspect;

import com.bank.accounts.exception.InsuficientFundsException;
import com.bank.accounts.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountsControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(InsuficientFundsException.class)
    public ResponseEntity<String> handleInsuficientFundsException(InsuficientFundsException insuficientFundsException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(insuficientFundsException.getMessage());
    }
}

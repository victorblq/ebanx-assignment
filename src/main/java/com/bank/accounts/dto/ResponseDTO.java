package com.bank.accounts.dto;

import com.bank.accounts.entity.Account;
import lombok.Data;

@Data
public class ResponseDTO {

    private Account origin;
    private Account destination;

    public ResponseDTO(Account destinationAccount){
        this.destination = destinationAccount;
    }

    public ResponseDTO(Account originAccount, Account destination){
        this.origin = originAccount;
        this.destination = destination;
    }
}

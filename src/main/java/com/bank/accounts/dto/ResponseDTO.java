package com.bank.accounts.dto;

import com.bank.accounts.entity.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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

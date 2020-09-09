package com.bank.accounts.dto;

import com.bank.accounts.entity.Account;
import com.bank.accounts.serializer.ResponseDTOSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(using = ResponseDTOSerializer.class)
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

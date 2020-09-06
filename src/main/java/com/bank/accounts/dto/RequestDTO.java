package com.bank.accounts.dto;

import com.bank.accounts.entity.Account;
import com.bank.accounts.enums.Event;
import lombok.Data;

@Data
public class RequestDTO {

    private Event eventType;
    private Integer origin;
    private Integer destination;
    private Double amount;
}

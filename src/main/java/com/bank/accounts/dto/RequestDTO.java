package com.bank.accounts.dto;

import com.bank.accounts.enums.Event;
import lombok.Data;

@Data
public class RequestDTO {

    private Event type;
    private Integer origin;
    private Integer destination;
    private int amount;
}

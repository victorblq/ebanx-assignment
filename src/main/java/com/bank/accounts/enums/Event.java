package com.bank.accounts.enums;

public enum Event {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    TRANSFER("transfer");

    public final String event;

    private Event(String event){
        this.event = event;
    }
}

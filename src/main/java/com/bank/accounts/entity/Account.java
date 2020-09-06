package com.bank.accounts.entity;

import com.bank.accounts.dto.RequestDTO;
import com.bank.accounts.exception.InsuficientFundsException;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Account {
    @Id
    private int id;

    @Column(name = "balance", columnDefinition = "double DEFAULT 0", nullable = false, precision = 2)
    private Double balance;

    public Account(RequestDTO requestDTO){
        this.id = requestDTO.getDestination();
    }

    public Account(int id, Double balance){
        this.id = id;
        this.balance = balance;
    }

    public void addBalance(Double amount){
        if(this.balance == null){
            this.balance = 0D;
        }

        this.balance = this.balance + amount;
    }

    public void withdrawBalance(Double amount) {
        if(amount > this.balance){
            throw new InsuficientFundsException("Insuficient funds");
        }

        this.balance = this.balance - amount;
    }
}

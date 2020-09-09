package com.bank.accounts.entity;

import com.bank.accounts.dto.RequestDTO;
import com.bank.accounts.exception.InsuficientFundsException;
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

    @Column(name = "balance", columnDefinition = "INT DEFAULT 0", nullable = false, precision = 2)
    private int balance;

    public Account(RequestDTO requestDTO){
        this.id = requestDTO.getDestination();
    }

    public Account(int id, int balance){
        this.id = id;
        this.balance = balance;
    }

    public void addBalance(int amount){
        this.balance = this.balance + amount;
    }

    public void withdrawBalance(int amount) {
        if(amount > this.balance){
            throw new InsuficientFundsException("Insuficient funds");
        }

        this.balance = this.balance - amount;
    }
}

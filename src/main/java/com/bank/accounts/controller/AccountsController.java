package com.bank.accounts.controller;

import com.bank.accounts.dto.RequestDTO;
import com.bank.accounts.dto.ResponseDTO;
import com.bank.accounts.entity.Account;
import com.bank.accounts.exception.NotFoundException;
import com.bank.accounts.repository.AccountsRepository;
import com.bank.accounts.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountsController {

    private AccountsService accountsService;
    private AccountsRepository accountsRepository;

    AccountsController(AccountsRepository accountsRepository, AccountsService accountsService){
        this.accountsService = accountsService;
        this.accountsRepository = accountsRepository;
    }

    @PostMapping("/event")
    public ResponseEntity<ResponseDTO> executeEvent(@RequestBody RequestDTO requestDTO) {
        switch (requestDTO.getType()){
            case DEPOSIT:
                return ResponseEntity.status(HttpStatus.CREATED).body(this.accountsService.deposit(requestDTO));
            case WITHDRAW:
                return ResponseEntity.status(HttpStatus.CREATED).body(this.accountsService.withdraw(requestDTO));
            case TRANSFER:
                return ResponseEntity.status(HttpStatus.CREATED).body(this.accountsService.transfer(requestDTO));
            default:
                return null;
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalanace(@RequestParam("account_id") Integer accountId) throws NotFoundException {
        return ResponseEntity.ok(
                this.accountsRepository.getBalanceById(accountId)
                        .orElseThrow(() -> new NotFoundException("0"))
        );
    }

    @PostMapping("/reset")
    public ResponseEntity reset(){
        this.accountsRepository.deleteAll();
        this.accountsRepository.save(new Account(300, 0));
        return ResponseEntity.ok("OK");
    }
}

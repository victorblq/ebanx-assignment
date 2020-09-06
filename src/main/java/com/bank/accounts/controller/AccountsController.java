package com.bank.accounts.controller;

import com.bank.accounts.dto.RequestDTO;
import com.bank.accounts.dto.ResponseDTO;
import com.bank.accounts.exception.BadRequestException;
import com.bank.accounts.exception.NotFoundException;
import com.bank.accounts.repository.AccountsRepository;
import com.bank.accounts.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private AccountsService accountsService;
    private AccountsRepository accountsRepository;

    AccountsController(AccountsRepository accountsRepository, AccountsService accountsService){
        this.accountsService = accountsService;
        this.accountsRepository = accountsRepository;
    }

    @PostMapping("/event")
    public ResponseEntity<ResponseDTO> executeEvent(@RequestBody RequestDTO requestDTO) {
        this.validateField(requestDTO.getEventType(), "eventType");

        switch (requestDTO.getEventType()){
            case DEPOSIT:
                return ResponseEntity.status(HttpStatus.CREATED).body(this.accountsService.deposit(requestDTO));
            case WITHDRAW:
                this.validateField(requestDTO.getAmount(), "amount");
                return ResponseEntity.status(HttpStatus.OK).body(this.accountsService.withdraw(requestDTO));
            case TRANSFER:
                this.validateField(requestDTO.getAmount(), "amount");
                return ResponseEntity.status(HttpStatus.OK).body(this.accountsService.transfer(requestDTO));
            default:
                return null;
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalanace(@RequestParam("account_id") Integer accountId) throws NotFoundException {
        return ResponseEntity.ok(
                this.accountsRepository.getBalanceById(accountId)
                        .orElseThrow(() -> new NotFoundException("Account not found"))
        );
    }

    @PostMapping("/reset")
    public ResponseEntity reset(){
        this.accountsRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    private void validateField(Object field, String fieldName){
        Optional.ofNullable(field)
                .orElseThrow(() -> new BadRequestException("Missing fields, verify the field: ", fieldName));
    }
}

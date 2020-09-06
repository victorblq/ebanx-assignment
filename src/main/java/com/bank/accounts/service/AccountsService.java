package com.bank.accounts.service;

import com.bank.accounts.dto.RequestDTO;
import com.bank.accounts.dto.ResponseDTO;
import com.bank.accounts.entity.Account;
import com.bank.accounts.exception.InsuficientFundsException;
import com.bank.accounts.exception.NotFoundException;
import com.bank.accounts.repository.AccountsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountsService {

    private AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
    }

    @Transactional
    public ResponseDTO deposit(RequestDTO requestDTO) {
        Account destinationAccount =
                this.findAccountById(requestDTO.getDestination()).orElse(new Account(requestDTO));

        destinationAccount.addBalance(requestDTO.getAmount());

        return new ResponseDTO(this.accountsRepository.save(destinationAccount));
    }

    @Transactional
    public ResponseDTO withdraw(RequestDTO requestDTO) throws InsuficientFundsException {
        Account destinationAccount =
                this.findAccountById(requestDTO.getDestination())
                        .orElseThrow(() -> new NotFoundException("Account not found"));

        destinationAccount.withdrawBalance(requestDTO.getAmount());

        return new ResponseDTO(this.accountsRepository.save(destinationAccount));
    }

    @Transactional
    public ResponseDTO transfer(RequestDTO requestDTO) {
        Account originAccount = this.findAccountById(requestDTO.getOrigin())
                .orElseThrow(() -> new NotFoundException("Origin account not found"));
        Account destinationAccount = this.findAccountById(requestDTO.getDestination())
                .orElseThrow(() -> new NotFoundException("Destination account not found"));

        originAccount.withdrawBalance(requestDTO.getAmount());
        destinationAccount.addBalance(requestDTO.getAmount());

        this.accountsRepository.save(originAccount);
        this.accountsRepository.save(destinationAccount);

        return new ResponseDTO(originAccount, destinationAccount);
    }

    private Optional<Account> findAccountById(Integer accountId){
        return this.accountsRepository.findById(accountId);
    }

}

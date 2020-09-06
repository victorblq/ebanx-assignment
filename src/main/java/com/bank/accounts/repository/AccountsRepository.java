package com.bank.accounts.repository;

import com.bank.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT account.balance FROM Account account WHERE account.id = :accountId")
    Optional<Double> getBalanceById(@Param("accountId") Integer accountId);
}

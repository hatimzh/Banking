package com.hatim.banking.repo;

import com.hatim.banking.model.Account;
import com.hatim.banking.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank,Integer> {

    Bank findByName(String name);

    @Query("SELECT a FROM Account a WHERE a.bank.id = :bankId")
    List<Account> findAllAccountsByBankId(@Param("bankId") int bankId);
}

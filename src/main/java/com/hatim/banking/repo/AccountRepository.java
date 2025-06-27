package com.hatim.banking.repo;

import com.hatim.banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findByBankName(String name);
}

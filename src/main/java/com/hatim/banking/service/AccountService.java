package com.hatim.banking.service;

import com.hatim.banking.model.Account;
import com.hatim.banking.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Account getAccountById(int id) {
        return this.accountRepository.findById(id).get();
    }

    public List<Account> getAccountsByBankName(String bank){
        return this.accountRepository.findByBankName(bank);
    }

    public void addAccount(Account account){
        this.accountRepository.save(account);
    }

    public void updateAccount(Account account){
        this.accountRepository.save(account);
    }

    public void deleteAccount(int id){
        this.accountRepository.deleteById(id);
    }
}
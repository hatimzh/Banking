package com.hatim.banking.service;

import com.hatim.banking.AccountSrvice;
import com.hatim.banking.model.Account;
import com.hatim.banking.model.Bank;
import com.hatim.banking.repo.AccountRepository;
import com.hatim.banking.repo.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Account getAccountById(int id) {
        return this.accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAccountsByBankName(String bank){
        return this.accountRepository.findByBankName(bank);
    }

    public void addAccount(Account account){

        if (account.getBank() != null && account.getBank().getId() != 0) {
            Bank bank = bankRepository.findById(account.getBank().getId()).orElse(null);
            account.setBank(bank);
        }
        this.accountRepository.save(account);
    }

    public void updateAccount(Account account){
        this.accountRepository.save(account);
    }

    public void deleteAccount(int id){
        this.accountRepository.deleteById(id);
    }

    
}
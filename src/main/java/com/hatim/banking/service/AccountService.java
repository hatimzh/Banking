package com.hatim.banking.service;

import com.hatim.banking.AccountSrvice;
import com.hatim.banking.model.Account;
import com.hatim.banking.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountSrvice {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
        this.accountRepository.save(account);
    }

    public void updateAccount(Account account){
        this.accountRepository.save(account);
    }

    public void deleteAccount(int id){
        this.accountRepository.deleteById(id);
    }

    // Implémentation des méthodes de l'interface AccountSrvice

    @Override
    public String deposit(int accountId, int amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Compte non trouvé";
        }
        try {
            account.deposit(amount); // utilise la méthode du modèle
            accountRepository.save(account);
            return String.format("%-15s || %-10s || %-10s%n%-15s ||  %-9s || %-10s%n",
                    "Date", "Amount", "Balance",
                    account.getOperationDate(), amount, account.getBalance());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String withdraw(int accountId, int amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Compte non trouvé";
        }
        try {
            account.withdraw(amount);
            accountRepository.save(account);
            return String.format("%-15s || %-10s || %-10s%n%-15s || - %-8s || %-10s%n",
                    "Date", "Amount", "Balance",
                    account.getOperationDate(), amount, account.getBalance());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String printBalance(int accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Compte non trouvé";
        }
        return "Balance = " + account.getBalance();
    }
}
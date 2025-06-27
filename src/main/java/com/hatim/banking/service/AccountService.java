package com.hatim.banking.service;

import com.hatim.banking.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hatim.banking.repo.AccountRepository;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountService {
    private AccountRepository acccountRepository;


    public List<Account> getAllAccounts() {
        return acccountRepository.findAll();
    }

    public Account getAccountById(int id) {
        return acccountRepository.findById(id).get();
    }

    public List<Account> getAccountsByBankName(String bank){
        return acccountRepository.findByBankName(bank);
    }

    public void addAccount(Account account){
        acccountRepository.save(account);
    }

    public void updateAccount(Account account){
        acccountRepository.save(account);
    }

    public void deleteAccount(int id){
        acccountRepository.deleteById(id);
    }

}

package com.hatim.banking.service;

import com.hatim.banking.model.Account;
import com.hatim.banking.model.Bank;
import com.hatim.banking.repo.AccountRepository;
import com.hatim.banking.repo.BankRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankService {
    private BankRepository bankRepository;


    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank getBankById(int id) {
        return bankRepository.findById(id).get();
    }

    public Bank getBankByName(String name) {
        return bankRepository.findByName(name);
    }

    public List<Account> getAccountsByBank(String bank) {
        Bank bank1=bankRepository.findByName(bank);
        return bankRepository.findAllAccountsByBankId(bank1.getId());
    }

    public void updateBank(Bank bank) {
        bankRepository.save(bank);
    }

    public void addBank(Bank bank) {
        bankRepository.save(bank);
    }

    public void removeBank(int id) {
        bankRepository.deleteById(id);
    }
}

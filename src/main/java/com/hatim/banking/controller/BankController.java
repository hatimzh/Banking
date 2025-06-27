package com.hatim.banking.controller;

import com.hatim.banking.model.Account;
import com.hatim.banking.model.Bank;
import com.hatim.banking.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class BankController {
    private BankService bankService;

    @GetMapping("/bank/all")
    public List<Bank> getAllBanks(){
        return bankService.getAllBanks();
    }

    @GetMapping("/bank/get/{id}")
    public Bank getBank(@PathVariable int id){
        return bankService.getBankById(id);
    }

    @GetMapping("/bank/get/{name}")
    public Bank getBankByName(@PathVariable String name){
        return bankService.getBankByName(name);
    }

    @GetMapping("/bank/{bank}/accounts")
    public List<Account> getBankAccounts(@PathVariable String bank){
        return bankService.getAccountsByBank(bank);
    }

    @PostMapping("/bank/add")
    public void addBank(@RequestBody Bank bank){
        bankService.addBank(bank);
    }

    @PutMapping("/bank/update")
    public void updateBank(@RequestBody Bank bank){
        bankService.updateBank(bank);
    }

    @DeleteMapping("/bank/delete/{id}")
    public void deleteBank(@PathVariable int id){
        bankService.removeBank(id);
    }
}

package com.hatim.banking.controller;

import com.hatim.banking.model.Account;
import com.hatim.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/get/all")
    public List<Account> getAllAccounts() {
        return this.accountService.getAllAccounts();
    }

    @GetMapping("/get/{id}")
    public Account getAccount(@PathVariable int id) {
        return this.accountService.getAccountById(id);
    }

    @GetMapping("/get/bank/{bank}")
    public List<Account> getAccountByBankName(@PathVariable String bank) {
        return this.accountService.getAccountsByBankName(bank);
    }

    @PostMapping("/add")
    public void addAccount(@RequestBody Account account){
        this.accountService.addAccount(account);
    }

    @PutMapping("/update")
    public void updateAccount(@RequestBody Account account){
        this.accountService.updateAccount(account);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable int id){
        this.accountService.deleteAccount(id);
    }
}
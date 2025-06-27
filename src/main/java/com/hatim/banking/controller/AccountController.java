package com.hatim.banking.controller;

import com.hatim.banking.model.Account;
import com.hatim.banking.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class AccountController {
    private AccountService accountService;

    @GetMapping("/account/get/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/get/{id}")
    public Account getAccount(@PathVariable int id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/account/get/bank/{bank}")
    public List<Account> getAccountByBankName(@PathVariable String bank) {
        return accountService.getAccountsByBankName(bank);
    }

    @PostMapping("/account/add")
    public void addAccount(@RequestBody Account account){
        accountService.addAccount(account);
    }

    @PutMapping("/account/update")
    public void updateAccount(@RequestBody Account account){
        accountService.updateAccount(account);
    }

    @DeleteMapping("/account/delete/{id}")
    public void deleteAccount(@PathVariable int id){
        accountService.deleteAccount(id);
    }

}

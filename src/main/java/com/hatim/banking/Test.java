package com.hatim.banking;

import com.hatim.banking.model.Account;
import com.hatim.banking.model.Bank;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {


    public static void main(String[] args) {
        Account account;
        Account account1;
        Bank bank;
        ArrayList<Account> accounts = new ArrayList<>();

        bank = new Bank(1,"CIH",accounts);

        account = new Account(1,1000,LocalDate.now(),bank);
        account1 = new Account(1,0,LocalDate.of(2020,2,13),bank);

        accounts.add(account);
        accounts.add(account1);

        bank.setAccounts(accounts);
        //Account 1 :

        account1.printBalance();
        account1.deposit(2000);
        account1.withdraw(1000);
        account1.printBalance();
        account1.withdraw(3000);

        //Acount 0 :

        account.printBalance();
        account.deposit(2000);
        account.withdraw(1000);
        account.printBalance();
    }


}

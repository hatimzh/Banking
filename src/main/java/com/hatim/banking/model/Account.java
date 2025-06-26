package com.hatim.banking.model;


import com.hatim.banking.AccountSrvice;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor

public class Account implements AccountSrvice {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private int balance;
    private LocalDate operationDate;

    @ManyToOne
    private Bank bank;


    @Override
    public void deposit(int amount) {
        this.operationDate = LocalDate.now();
        this.balance += amount;
        System.out.printf("%-15s || %-10s || %-10s%n", "Date", "Amount", "Balance");

        System.out.printf("%-15s ||  %-9s || %-10s%n", operationDate, amount, balance);

    }

    @Override
    public void withdraw(int amount) {
        this.operationDate = LocalDate.now();
        if(this.balance < amount) {
            throw new RuntimeException("******Insufficient Balance******");
        }
        this.balance -= amount;

        System.out.printf("%-15s || %-10s || %-10s%n", "Date", "Amount", "Balance");

        System.out.printf("%-15s || - %-8s || %-10s%n", operationDate, amount, balance);


    }

    @Override
    public void printBalance() {
        System.out.println("Balance = "+this.balance);
    }

    public void printOperations(){

    }


}

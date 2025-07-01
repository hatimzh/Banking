package com.hatim.banking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hatim.banking.AccountSrvice;
import jakarta.persistence.*;
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
    @JoinColumn(name = "bank_id")
    @JsonIgnoreProperties({"accounts"})
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
            System.out.println("******Insufficient Balance******");
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


}

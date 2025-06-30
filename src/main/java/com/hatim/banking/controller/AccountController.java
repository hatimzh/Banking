package com.hatim.banking.controller;

import com.hatim.banking.model.Account;
import com.hatim.banking.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
@Tag(name = "Compte", description = "API de gestion des comptes bancaires")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Récupérer tous les comptes",
            description = "Retourne la liste de tous les comptes bancaires")
    @ApiResponse(responseCode = "200", description = "Liste des comptes récupérée avec succès")
    @GetMapping("/get/all")
    public List<Account> getAllAccounts() {
        return this.accountService.getAllAccounts();
    }

    @Operation(summary = "Récupérer un compte par son ID",
            description = "Retourne un compte bancaire en fonction de son identifiant")
    @ApiResponse(responseCode = "200", description = "Compte trouvé")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @GetMapping("/get/{id}")
    public Account getAccount(
            @Parameter(description = "ID du compte à rechercher")
            @PathVariable int id) {
        return this.accountService.getAccountById(id);
    }

    @Operation(summary = "Rechercher des comptes par nom de banque",
            description = "Retourne tous les comptes associés à une banque spécifique")
    @GetMapping("/get/bank/{bank}")
    public List<Account> getAccountByBankName(
            @Parameter(description = "Nom de la banque")
            @PathVariable String bank) {
        return this.accountService.getAccountsByBankName(bank);
    }

    @Operation(summary = "Ajouter un nouveau compte",
            description = "Crée un nouveau compte bancaire")
    @ApiResponse(responseCode = "200", description = "Compte créé avec succès")
    @PostMapping("/add")
    public void addAccount(
            @Parameter(description = "Détails du compte à créer")
            @RequestBody Account account){
        this.accountService.addAccount(account);
    }

    @Operation(summary = "Mettre à jour un compte",
            description = "Modifie les informations d'un compte existant")
    @ApiResponse(responseCode = "200", description = "Compte mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @PutMapping("/update")
    public void updateAccount(
            @Parameter(description = "Détails du compte à mettre à jour")
            @RequestBody Account account){
        this.accountService.updateAccount(account);
    }

    @Operation(summary = "Supprimer un compte",
            description = "Supprime un compte bancaire par son ID")
    @ApiResponse(responseCode = "200", description = "Compte supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @DeleteMapping("/delete/{id}")
    public void deleteAccount(
            @Parameter(description = "ID du compte à supprimer")
            @PathVariable int id){
        this.accountService.deleteAccount(id);
    }

    @Operation(summary = "Déposer de l'argent sur un compte")
    @PostMapping("/deposit")
    public void deposit(@RequestBody Account account, @RequestParam int amount) {
        // On recharge le compte depuis la base pour éviter toute fraude
        Account acc = accountService.getAccountById(account.getId());
        if (acc != null) {
            acc.deposit(amount);
            accountService.updateAccount(acc);
        }
    }

    @Operation(summary = "Retirer de l'argent d'un compte")
    @PostMapping("/withdraw")
    public void withdraw(@RequestBody Account account, @RequestParam int amount) {
        Account acc = accountService.getAccountById(account.getId());
        if (acc != null) {
            acc.withdraw(amount);
            accountService.updateAccount(acc);
        }
    }

    @Operation(summary = "Afficher le solde d'un compte")
    @PostMapping("/balance")
    public void printBalance(@RequestBody Account account) {
        Account acc = accountService.getAccountById(account.getId());
        if (acc != null) {
            acc.printBalance();
        }
    }
}

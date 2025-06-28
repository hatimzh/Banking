package com.hatim.banking.controller;

import com.hatim.banking.model.Account;
import com.hatim.banking.model.Bank;
import com.hatim.banking.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bank")
@Tag(name = "Bank", description = "API de gestion des banques")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @Operation(summary = "Récupérer toutes les banques",
            description = "Retourne la liste complète des banques enregistrées dans le système")
    @ApiResponse(responseCode = "200", description = "Liste des banques récupérée avec succès")
    @GetMapping("/all")
    public List<Bank> getAllBanks(){
        return bankService.getAllBanks();
    }

    @Operation(summary = "Récupérer une banque par son ID",
            description = "Retourne les détails d'une banque spécifique en fonction de son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Banque trouvée"),
            @ApiResponse(responseCode = "404", description = "Banque non trouvée")
    })
    @GetMapping("/get/id/{id}")
    public Bank getBank(@Parameter(description = "ID de la banque à rechercher")
                            @PathVariable int id){
        return bankService.getBankById(id);
    }

    @Operation(summary = "Rechercher des banques par nom",
            description = "Retourne toutes les banques dont le nom contient la chaîne de recherche")
    @ApiResponse(responseCode = "200", description = "Recherche effectuée avec succès")
    @GetMapping("/get/{name}")
    public Bank getBankByName(@Parameter(description = "Nom ou partie du nom de la banque")
                                  @PathVariable String name){
        return bankService.getBankByName(name);
    }

    @Operation(summary = "Rechercher des comptes par nom du bank",
            description = "Retourne toutes les comptes dont le nom du bank contient la chaîne de recherche")
    @ApiResponse(responseCode = "200", description = "Recherche effectuée avec succès")
    @GetMapping("/{bank}/accounts")
    public List<Account> getBankAccounts(@Parameter(description = "Nom ou partie du nom de la banque")
                                             @PathVariable String bank){
        return bankService.getAccountsByBank(bank);
    }

    @Operation(summary = "Créer une nouvelle banque",
            description = "Ajoute une nouvelle banque dans le système")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Banque créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données de la banque invalides")
    })

    @PostMapping("/add")
    public void addBank(@Parameter(description = "Détails de la banque à créer")
                            @RequestBody Bank bank){
        bankService.addBank(bank);
    }

    @Operation(summary = "Mettre à jour une banque",
            description = "Modifie les informations d'une banque existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Banque mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Banque non trouvée"),
            @ApiResponse(responseCode = "400", description = "Données de mise à jour invalides")
    })

    @PutMapping("/update")
    public void updateBank(@Parameter(description = "Nouvelles informations de la banque")
                               @RequestBody Bank bank){
        bankService.updateBank(bank);
    }

    @Operation(summary = "Supprimer une banque",
            description = "Supprime une banque du système par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Banque supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Banque non trouvée"),
            @ApiResponse(responseCode = "409", description = "La banque ne peut pas être supprimée car elle est liée à des comptes")
    })

    @DeleteMapping("/delete/{id}")
    public void deleteBank(@Parameter(description = "ID de la banque à supprimer")
                               @PathVariable int id){

        bankService.removeBank(id);
    }
}

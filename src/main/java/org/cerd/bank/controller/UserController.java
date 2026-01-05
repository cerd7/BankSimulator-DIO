package org.cerd.bank.controller;

import org.cerd.bank.service.AccountService;
import org.cerd.bank.ui.MainMenuUI;
import org.cerd.bank.exception.InvalidCpfException;
import org.cerd.bank.model.Account;

public class UserController{
    private final AccountService accountService;
    private final MainMenuUI mainMenuUi;

    public UserController(AccountService accountService, MainMenuUI mainMenuUi){
        this.accountService = accountService;
        this.mainMenuUi = mainMenuUi;
    }

    public void start(){
        mainMenuUi.showWelcome();
        handleUserChoice();
    }

    private void handleUserChoice(){
        int option = mainMenuUi.getFirstTimeOption();
        switch(option){
            case 1 -> handleNewUser();
            case 2 -> handleExistingUser();
        }
    }

    private void handleExistingUser(){
        System.out.println("Login functionality coming soon...");
    }

    private void handleNewUser(){
        mainMenuUi.showBankInfo();
        if(mainMenuUi.confirmCreateAccount()){
            createNewAccount();
        }else{
            System.out.println("Thank you for visiting! Come back anytime.");
        }
    }


    private void createNewAccount(){
        try{
            MainMenuUI.UserDataDTO data = mainMenuUi.collectionUserData();

            Account createAccount = accountService.createAccount(
                data.name(),
                data.age(),
                data.cpf(),
                data.phone(),
                data.password()
            );

            System.out.println("\n Account created sucessfully!");
            System.out.println("Your account number is: " + createAccount.getAccountID());
            System.out.println("Plase save this number for future acess.");
        }catch(InvalidCpfException e){
            System.out.println("\n Error: Invalid CPF - " + e.getMessage());
        }catch(RuntimeException e){
            System.out.println("\n Error creating account: " + e.getMessage());
        }
    }
}
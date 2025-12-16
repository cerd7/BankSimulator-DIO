package org.cerd.bank.controller;

import org.cerd.bank.service.AccountService;
import org.cerd.bank.ui.MainMenuUI;
import org.cerd.bank.model.User;

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

    }

    private void handleNewUser(){
        mainMenuUi.showBankInfo();
        if(mainMenuUi.confirmCreateAccount()){
            createNewAccount();
        }
    }


    private void createNewAccount(){
        User userData = mainMenuUi.collectUserData();
        accountService.createAccout(
            userData.getName(),
            userData.getAge(),
            userData.getCpf(),
            userData.getCellPhone()
        );
    }
}
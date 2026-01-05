package org.cerd.bank;

import org.cerd.bank.controller.UserController;
import org.cerd.bank.repository.AccountRepository;
import org.cerd.bank.service.AccountService;
import org.cerd.bank.ui.MainMenuUI;
import org.cerd.bank.util.HashUtil;
import org.cerd.bank.validator.AccountValidator;
import org.cerd.bank.validator.CpfValidator;
import org.cerd.bank.validator.UserValidator;

public class Main {
    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();

        HashUtil hashUtil = new HashUtil();

        CpfValidator cpfValidator = new CpfValidator();

        UserValidator userValidator = new UserValidator();

        AccountValidator accountValidator = new AccountValidator(
            accountRepository, 
            hashUtil
        );

        AccountService accountService = new AccountService(
            accountRepository, 
            accountValidator, 
            hashUtil, 
            null, 
            cpfValidator, 
            userValidator
        );

        MainMenuUI mainMenuUI = new MainMenuUI();

        UserController userController = new UserController(
            accountService, 
            mainMenuUI
        );

        try{
            userController.start();
        }finally{
            mainMenuUI.close();
        }
    }
}
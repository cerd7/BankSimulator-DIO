package org.cerd.bank;

import org.cerd.bank.controller.UserController;
import org.cerd.bank.repository.AccountRepository;
import org.cerd.bank.service.AccountService;
import org.cerd.bank.ui.MainMenuUI;
import org.cerd.bank.util.AccountIdGenerator;
import org.cerd.bank.util.HashUtil;
import org.cerd.bank.validator.AccountValidator;

/**
 * Classe principal da aplicação CERD Bank.
 * Responsável por inicializar as dependências e iniciar o sistema.
 */
public class Main {
    
    public static void main(String[] args) {
        // Configuração e injeção de dependências (Composition Root)
        try (MainMenuUI mainMenuUI = new MainMenuUI()) {
            
            // Utilitários
            HashUtil hashUtil = new HashUtil();
            AccountIdGenerator accountIdGenerator = new AccountIdGenerator();
            
            // Repositórios
            AccountRepository accountRepository = new AccountRepository();
            
            // Validadores
            AccountValidator accountValidator = new AccountValidator(accountRepository, hashUtil);
            
            // Serviços
            AccountService accountService = new AccountService(
                    accountRepository,
                    accountValidator,
                    hashUtil,
                    accountIdGenerator
            );
            
            // Controladores
            UserController userController = new UserController(accountService, mainMenuUI);
            
            // Inicia a aplicação
            userController.start();
            
        } catch (Exception e) {
            System.err.println("Erro fatal na aplicação: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
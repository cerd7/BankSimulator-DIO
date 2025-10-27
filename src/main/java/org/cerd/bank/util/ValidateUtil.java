package org.cerd.bank.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.model.Account;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ValidateUtil {
    private List<Account> accounts;
    private Scanner scannerOperations;
    private boolean checkCondition = true;
    private String receiveCpf;
    private final String file = "src/main/resources/users.json";

    public ValidateUtil() {
        scannerOperations = new Scanner(System.in);
    }

    public boolean validateAccount(String cpf, String hash) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            accounts = objectMapper.readValue(new File(file), objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
            for (Account validateInfosAccount : accounts) {
                if (validateInfosAccount.getInfoUser().getCpf().equals(cpf) && validateInfosAccount.getInfoUser().getHash().equals(hash)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading a file: " + e.getMessage());
        }
        return false;
    }

    public boolean validateLogin(String accountID, String passwordID) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            accounts = objectMapper.readValue(new File(file), objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
            for (Account validateInfosAccount : accounts) {
                if (validateInfosAccount.getAccountID().equals(accountID) && validateInfosAccount.getPasswordID().equals(passwordID)) {
                    System.out.println("Welcome " + validateInfosAccount.getInfoUser().getName() + " How can I help you today? ");
                    return true;
                }
                else if (!validateInfosAccount.getAccountID().equals(accountID)) {
                    System.out.println("Invalid account ID. Try again.");
                    return false;
                }
                else {
                    System.out.println("Invalid password. Try again.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading a file: " + e.getMessage());
        }
        return false;
    }

    public boolean validateCpf(String cpf) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            accounts = objectMapper.readValue(new File(file), objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
            for (Account validateInfoCpf : accounts) {
                if (validateInfoCpf.getInfoUser().getCpf().equals(cpf)) {
                    System.out.println("CPF validated!");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid CPF: " + e.getMessage());
        }
        return false;
    }

    public boolean validateAge(Integer age) {
        if (age < 18) {
            System.out.println("I'm sorry, but you're too young to have a bank account... try again in the future!");
        }
        return false;
    }

    //Atribuir essa função para a classe validateUtil
    public boolean twoFactorValidate() {
        System.out.println("As a precaution, enter your CPF to validate \n that the account exists in the system:");
        while (checkCondition) {
            receiveCpf = scannerOperations.next();
            if (receiveCpf.length() != 11) {
                System.out.println("The CPF entered does not contain the required number of characters... \n Try again:");
            } else {
                if (validateCpf(receiveCpf)) {
                    System.out.println("Identify an account with this CPF... \n Do you remember your PIN?");
                    checkCondition = false;
                    return false;
                } else {
                    System.out.println("User not found... \n You need to create an account!");
                    break;
                }
            }
        }
        return true;
    }
}
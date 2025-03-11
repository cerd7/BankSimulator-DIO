package org.cerd.bank.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.model.Account;
import org.cerd.bank.model.User;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ValidateUtil extends User {
    private List<Account> accounts;
    private final String file = "src/main/resources/users.json";

    public ValidateUtil() {
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
        //System.out.println("User can not fund...");
        return false;
    }

    public boolean validateAge(Integer age) {
        if (age < 18) {
            System.out.println("I'm sorry, but you're too young to have a bank account... try again in the future!");
        }
        //System.out.println("User can not fund...");
        return false;
    }


}
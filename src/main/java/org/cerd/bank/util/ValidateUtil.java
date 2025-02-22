package org.cerd.bank.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.model.User;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ValidateUtil extends User{
    private User validUser = new User();

    public ValidateUtil() {
    }

    public boolean validate(String cpf, String hash) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> users = objectMapper.readValue(new File(getFile()), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            for (User user : users) {
                if (user.getCpf().equals(cpf) && user.getHash().equals(hash)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading a file: " + e.getMessage());
        }
        System.out.println("User can not fund: ");
        return false;
    }

    public boolean validateCpf(String cpf) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> users = objectMapper.readValue(new File(getFile()), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            for (User user : users) {
                if (user.getCpf().equals(cpf)) {
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
    public boolean limiterAge(Integer age) {
        if (age < 18)
        {
            System.out.println("I'm sorry, but you're too young to have a bank account... try again in the future!");
        }
        //System.out.println("User can not fund...");
        return false;
    }

    public User getValidUser() {
        return validUser;
    }
}
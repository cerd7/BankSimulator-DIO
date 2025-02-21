package org.cerd.bank.trash;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Deposit extends User {
    public Deposit() {
    }

    public static void newDeposit(String cpf, Double amount) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(new File(getFile()), new TypeReference<List<User>>() {
        });

        boolean userFound = false;
        for (User user : users) {
            if (user.getCpf().equals(cpf)) {
                if (user.getBalance() == null) {
                    user.setBalance(amount);
                } else {
                    user.setBalance(user.getBalance() + amount);
                }
                userFound = true;
                System.out.println("User found!");
                break;
            }
        }

        if (!userFound) {
            System.out.println("User not found!");
            return;
        }

        objectMapper.writeValue(new File(getFile()), users);
        System.out.println("Deposit with successful!");
    }
}

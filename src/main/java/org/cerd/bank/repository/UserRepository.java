package org.cerd.bank.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.model.Account;
import org.cerd.bank.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final File file = new File("src/main/resources/users.json");
    private List<Account> accounts;

    public void readUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            accounts = objectMapper.readValue(new File(String.valueOf(file)), objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
            for (Account readInfos : accounts) {
                System.out.println(readInfos.getInfoUser().getName() + " - " + readInfos.getInfoUser().getCpf() + " - " + readInfos.getInfoUser().getHash() + " - " + readInfos.getInfoUser().getBalance());
            }
        } catch (IOException e) {
            System.out.println("Error reading a file: " + e.getMessage());
        }
    }

    public void readBalance() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<User> users = objectMapper.readValue(new File(String.valueOf(file)), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            for (User user : users) {
                System.out.println("See your balance: " + user.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Your account is empty...");
        }
    }

    public void addUser(Account user) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (file.exists() && file.length() > 0) {
                accounts = objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
                accounts.add(user);
                objectMapper.writeValue(file, accounts);
                System.out.println("New user added.");
            } else {
                accounts = new ArrayList<>();
                accounts.add(user);
                objectMapper.writeValue(file, accounts);
                System.out.println("New file created and user added.");
            }
        } catch (IOException e) {
            System.out.println("Error reading or creating the JSON file: " + e.getMessage());
        }
    }
}

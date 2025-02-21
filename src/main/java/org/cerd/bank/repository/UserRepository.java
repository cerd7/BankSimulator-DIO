package org.cerd.bank.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserRepository extends User {
    public void readUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> users = objectMapper.readValue(new File(getFile()), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            for (User user : users) {
                System.out.println(user.getName() + " - " + user.getHash() + " - " + user.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Error reading a file: " + e.getMessage());
        }
    }
}

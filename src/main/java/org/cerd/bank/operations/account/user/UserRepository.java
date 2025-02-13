package org.cerd.bank.operations.account.user;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class UserRepository
{
    private static final String FILE_NAME = "src/main/resources/users.json";

    public static void saveUser(User user)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            objectMapper.writeValue(new File(FILE_NAME), user);
            System.out.println("User save with successful");
        }catch (IOException e){
            System.out.println("Error validating user: " + e.getMessage());
        }
    }

    public static void readUser()
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            User user = objectMapper.readValue(new File(FILE_NAME), User.class);
            System.out.println(user.getName() + " - " + user.getCpf());
        }catch (IOException e){
            System.out.println("Error reading a file: " + e.getMessage());
        }
    }
}

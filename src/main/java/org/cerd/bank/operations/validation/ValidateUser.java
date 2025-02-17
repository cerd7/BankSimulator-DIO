package org.cerd.bank.operations.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.operations.account.user.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ValidateUser
{
    public ValidateUser()
    {
    }
    private final static String FILE_NAME = "src/main/resources/users.json";
    public boolean validate(String cpf, String hash)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            List<User> users = objectMapper.readValue(new File(FILE_NAME), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            for(User user : users)
            {
                if(user.getCpf().equals(cpf) && user.getHash().equals(hash))
                {
                    return true;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading a file: " + e.getMessage());
        }
        System.out.println("User can not fund: ");
        return false;
    }
}
package org.cerd.bank.operations.account.user;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ValidateUser extends User
{
    public ValidateUser()
    {
    }
    public boolean validate(String cpf, String hash)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            List<User> users = objectMapper.readValue(new File(getFile()), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
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
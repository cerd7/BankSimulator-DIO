package org.cerd.bank.operations.noncustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.operations.account.user.User;
import org.cerd.bank.operations.validation.ValidateUser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateAccount
{
    ValidateUser validate = new ValidateUser();

    private static final String FILE_NAME = "src/main/resources/users.json";

    public void createNewAccount(String name, Integer age, String cellPhone, String cpf)
    {
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setCpf(cpf);
        newUser.setCellPhone(cellPhone);

        ValidateUser validateUser = new ValidateUser();

        if(validateUser.validate(cpf, newUser.getHash()))
        {
            System.out.println("Usuário já existe. Conta não pode ser criada.");
        }
        else
        {
            System.out.println("Usuário não encontrado. Criando conta...");
            addUser(newUser);
        }
    }

    private void addUser(User user) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File("src/main/resources/users.json");
            if (file.exists() && file.length() > 0) {
                List<User> users = objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
                users.add(user);
                objectMapper.writeValue(file, users);
                System.out.println("Novo usuário adicionado.");

            } else {
                List<User> users = List.of(user);
                objectMapper.writeValue(file, users);
                System.out.println("Novo arquivo criado e usuário adicionado.");
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler ou criar o arquivo JSON: " + e.getMessage());
        }
    }
}

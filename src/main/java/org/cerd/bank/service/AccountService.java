package org.cerd.bank.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cerd.bank.model.Account;
import org.cerd.bank.model.User;
import org.cerd.bank.repository.UserRepository;
import org.cerd.bank.util.ValidateUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class AccountService{
    private final File file = new File("src/main/resources/users.json");

    UserRepository userRepository;
    ValidateUtil validateUtil;

    public AccountService() {
        userRepository = new UserRepository();
        validateUtil = new ValidateUtil();
    }

    public void createAccount(String name, Integer age, String cpf, String cellPhone) {
        Account account = new Account();
        User newUser = new User();

        newUser.setName(name);
        newUser.setAge(age);
        newUser.setCpf(cpf);
        newUser.setCellPhone(cellPhone);
        String hash = hashGenerate(name, cpf);
        newUser.setHash(hash);

        account.setAccountID("2348");
        account.setPasswordID("1234");
        account.setInfoUser(newUser);

        if (validateUtil.validateAccount(cpf, account.getInfoUser().getHash())) {
            System.out.println("User already exists. Account cannot be created.");
        } else {
            System.out.println("User not found. Creating account...");
            userRepository.addUser(account);
        }
    }

    public void newDeposit(String cpf, Double amount) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(new File(String.valueOf(file)), new TypeReference<>() {
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

        objectMapper.writeValue(new File(String.valueOf(file)), users);
        System.out.println("Deposit with successful!");
    }

    public boolean transfer(String name, String CPF) {
        return false;
    }

    public boolean withdrawal(Double value) {
        return false;
    }

    public String hashGenerate(String name, String cpf) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = name + cpf;
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error to generate hash:" + e.getMessage());
        }
    }

}

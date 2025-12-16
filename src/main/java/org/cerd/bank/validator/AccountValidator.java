package org.cerd.bank.validator;

import org.cerd.bank.repository.UserRepository;

public class AccountValidator{
    private final UserRepository userRepository;

    public AccountValidator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean accountExists(String cpf){
        return userRepository.findByCpf(cpf).isPresent();
    }

    public boolean validateCredentials(String accountId, String password){
        return userRepository.findAll().stream()
        .anyMatch(account -> 
            account.getAccountID().equals(accountId) &&
            account.getPasswordID().equals(password)
        );
    }
}
package org.cerd.bank.service;

import org.cerd.bank.model.Account;
import org.cerd.bank.model.User;
import org.cerd.bank.repository.UserRepository;

public class AccountService{
    private final UserRepository userRepository;
    private final AccountValidator accountValidator;
    private final HashUtil hashUtil;
    private final AccountIdGenerator accountIdGenerator;

    public AccountService(UserRepository userRepository, 
                          AccountValidator accountValidator,
                          HashUtil hashUtil,
                          AccountIdGenerator accountIdGenerator){
        this.userRepository = userRepository;
        this.accountValidator = accountValidator;
        this.hashUtil = hashUtil;
        this.accountIdGenerator = accountIdGenerator;
    }

    public Account createAccount(String name, Integer age, String cpf, String cellPhone){
        if(accountValidator.accountExists(cpf)){
            throw new AccountAlreadyExistsException("User with CPF " + cpf + " already exists");
        }

        User newUser = buildUser(name, age, cpf, cellPhone);
        Account account = buildAccount(newUser);

        userRepository.save(account);
        return account;
    }    

    private User buildUser(User user){
        Account account = new Account();
        account.setAccountID(accountIdGenerator.generate());
        account.setPasswordID(generateTemporaryPassword());
        account.setInfoUser(user);
        return account;
    }

    private void deposit(String cpf, Double amount){
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        Account account = userRepository.findByCpf(cpf)
        .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        User user = account.getInfoUser();
        double currentBalance = user.getBalnce() != null ? user.getBalance() : 0.0;
        user.setBalance(currentBalance + amount);
        userRepository.update(account);

    }
}
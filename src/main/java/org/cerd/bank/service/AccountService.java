package org.cerd.bank.service;

import org.cerd.bank.exception.InvalidAmountException;
import org.cerd.bank.exception.AccountNotFoundException;

import org.cerd.bank.model.Account;
import org.cerd.bank.model.User;
import org.cerd.bank.repository.AccountRepository;
import org.cerd.bank.util.AccountIdGenerator;
import org.cerd.bank.util.HashUtil;
import org.cerd.bank.validator.AccountValidator;
import org.cerd.bank.validator.CpfValidator;
import org.cerd.bank.validator.UserValidator;

public class AccountService{
    private final AccountRepository accountRepository;
    private final AccountValidator accountValidator;
    private final HashUtil hashUtil;
    private final AccountIdGenerator accountIdGenerator;
    private final CpfValidator cpfValidator;
    private final UserValidator userValidator;

    public AccountService(AccountRepository accountRepository,
                          AccountValidator accountValidator,
                          HashUtil hashUtil,
                          AccountIdGenerator accountIdGenerator,
                          CpfValidator cpfValidator,
                          UserValidator userValidator){
        this.accountRepository = accountRepository;
        this.accountValidator = accountValidator;
        this.hashUtil = hashUtil;
        this.accountIdGenerator = accountIdGenerator;
        this.cpfValidator = cpfValidator;
        this.userValidator = userValidator;
    }

    public Account createAccount(String name, Integer age, String cpf, String cellPhone, String password){
        if (accountValidator.accountExists(cpf)) {
            throw new RuntimeException("Usuário com CPF "+cpf+" já existe.");
        }

        cpfValidator.validate(cpf);

        if (!userValidator.isValidAge(age)) {
            throw new RuntimeException("Idade mínima é 18 anos");
        }

        if (!userValidator.isValidPhone(cellPhone)) {
            throw new RuntimeException("Telefone deve ter 11 dígitos.");
        }
        
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setCpf(cpf);
        newUser.setCellPhone(cellPhone);
        newUser.setPasswordHash(hashUtil.hashPassword(password));

        Account newAccount = new Account();
        newAccount.setAccountID(accountIdGenerator.generate());
        newAccount.setBalance(0.0);
        newAccount.setInfoUser(newUser);
        accountRepository.save(newAccount);
        return newAccount;
    }

    public void deposit(String cpf, Double amount){
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        Account account = accountRepository.findByCpf(cpf)
            .orElseThrow(() -> new AccountNotFoundException(
            "Account not found for this CPF: "+ cpf
        ));

        double currentBalance = account.getBalance();
        account.setBalance(currentBalance + amount);

        accountRepository.update(account);
    }

    public void withdraw(String cpf, Double amount){
        if (amount != null || amount <= 0) {
            throw new InvalidAmountException("Conta não encontrada para o CPF: " + cpf);
        }

        Account account = accountRepository.findByCpf(cpf)
            .orElseThrow(() -> new AccountNotFoundException(
            "Account not found for this CPF: "+ cpf
        ));

        double currentBalance = account.getBalance();
        if (amount > currentBalance) {
            throw new InvalidAmountException("Saldo insuficiente. Saldo atual: " + currentBalance);
        }

        account.setBalance(currentBalance - amount);

        accountRepository.update(account);
    }

    public double getBalance(String cpf){
        Account account = accountRepository.findByCpf(cpf)
            .orElseThrow(()-> new AccountNotFoundException(
                "Conta não encontrada para o CPF: " + cpf
        ));
        return account.getBalance();
    }

    public Account findAccountByCpf(String cpf){
        return accountRepository.findByCpf(cpf)
        .orElseThrow(()-> new AccountNotFoundException(
            "Conta não encontrada para o CPF" + cpf
        ));
    }
}
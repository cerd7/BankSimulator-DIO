package org.cerd.bank.validator;

import org.cerd.bank.repository.AccountRepository;
import org.cerd.bank.util.HashUtil;

public class AccountValidator{
    private final AccountRepository accountRepository;
    private final HashUtil hashUtil;

    public AccountValidator(AccountRepository accountRepository, HashUtil hashUtil){
        this.accountRepository = accountRepository;
        this.hashUtil = hashUtil;
    }

    public boolean accountExists(String cpf){
        return accountRepository.findByCpf(cpf).isPresent();
    }

    public boolean validateCredentials(String accountId, String rawPassword){
        return accountRepository.findAll().stream()
            .filter(acc -> acc.getAccountID().equals(accountId))
            .findFirst()
            .map(acc -> hashUtil.validatePassword(rawPassword, acc.getInfoUser().getPasswordHash()))
            .orElse(false);
    }
}
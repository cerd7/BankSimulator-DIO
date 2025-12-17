package org.cerd.bank.repository;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.cerd.bank.config.AppConfig;
import org.cerd.bank.exception.RepositoryException;
import org.cerd.bank.model.Account;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountRepository{
    private final ObjectMapper objectMapper;
    private final File dataFile;

    public AccountRepository(){
        this.objectMapper = new ObjectMapper();
        this.dataFile = new File(AppConfig.USERS_FILE_PATH);
    }

    public List<Account> findAll() {
        if (!dataFile.exists() || dataFile.length() == 0) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(dataFile, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
        } catch (IOException e) {
            throw new RepositoryException("Error reading accounts", e);
        }
    }

    public Optional<Account> findByCpf(String cpf){
        return findAll().stream()
               .filter(account -> account.getInfoUser().getCpf().equals(cpf))
               .findFirst();
    }

    public void save(Account account){
        List<Account> accounts = findAll();
        accounts.add(account);
        writeAll(accounts);
    }

    public void update(Account updateAccount){
        List<Account> accounts = findAll();
        accounts.replaceAll(account -> account.getInfoUser().getCpf().equals(updateAccount.getInfoUser().getCpf())
            ? updateAccount
            : account
        );
        writeAll(accounts);
    }

    private void writeAll(List<Account> accounts){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, accounts);
        } catch(IOException e){
            throw new RepositoryException("Error writing accounts", e);
        }
    }
}
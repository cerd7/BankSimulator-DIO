package org.cerd.bank.repository;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.cerd.bank.config.AppConfig;
import org.cerd.bank.exception.RepositoryException;
import org.cerd.bank.model.Account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountRepository{
    private final ObjectMapper objectMapper;
    private final File dataFile;
    private List<Account> accountCache;

    public AccountRepository(){
        this.objectMapper = new ObjectMapper();
        this.dataFile = new File(AppConfig.USERS_FILE_PATH);
        this.accountCache = new ArrayList<>();
        loadData();
    }

    private void loadData(){
        if(!dataFile.exists() || dataFile.length() == 0){
            this.accountCache = new ArrayList<>();
            return;
        }try{
            this.accountCache = objectMapper.readValue(
                dataFile, 
                new TypeReference<List<Account>>(){}
            );
        }catch(IOException e){
            throw new RepositoryException("Erro ao carregar contas", e);
        }
    }

    public List<Account> findAll() {
        return new ArrayList<>(accountCache);
    }

    public Optional<Account> findByCpf(String cpf){
        return accountCache.stream()
               .filter(acc -> acc.getInfoUser().getCpf().equals(cpf))
               .findFirst();
    }

    public Optional<Account> findByAccountId(String accountId){
        return accountCache.stream()
            .filter(acc -> acc.getAccountID().equals(accountId))
            .findFirst();
    }

    public void save(Account account){
        accountCache.add(account);
        writeAll();
    }

    public void update(Account updateAccount){
        for(int i = 0; i < accountCache.size(); i++){
            if (accountCache.get(i).getAccountID().equals(updateAccount.getAccountID())) {
                accountCache.set(i, updateAccount);
                break;
            }
        }
        writeAll();
    }

    private void writeAll(){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, accountCache);
        } catch(IOException e){
            throw new RepositoryException("Erro ao salvar contas", e);
        }
    }
}
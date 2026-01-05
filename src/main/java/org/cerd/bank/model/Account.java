package org.cerd.bank.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private String accountID;
    private double balance = 0.0;
    private User infoUser;

    public Account() {
    }

    public Account(String accountID, double balance, User infoUser) {
        this.accountID = accountID;
        this.balance = balance;
        this.infoUser = infoUser;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public User getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(User infoUser) {
        this.infoUser = infoUser;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return Objects.equals(accountID, account.accountID);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(accountID);
    }

    @Override
    public String toString(){
        return "Account{accountID='%s', balance='%s'}".formatted(accountID, balance);
    }
}

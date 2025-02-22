package org.cerd.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private String accountID;
    private String passwordID;
    private User infoUser;

    public Account() {
    }

    public Account(String accountID, String passwordID, User infoUser) {
        this.accountID = accountID;
        this.passwordID = passwordID;
        this.infoUser = infoUser;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getPasswordID() {
        return passwordID;
    }

    public void setPasswordID(String passwordID) {
        this.passwordID = passwordID;
    }

    public User getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(User infoUser) {
        this.infoUser = infoUser;
    }
}

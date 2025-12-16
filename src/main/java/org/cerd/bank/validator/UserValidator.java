package org.cerd.bank.validator;

import org.cerd.bank.config.AppConfig;

public class UserValidator {

    public boolean isValidAge(Integer age){
        return age != null && age >= AppConfig.MINIMUM_AGE;
    }

    public boolean isValidCpf(String cpf){
        return cpf != null && cpf.length() == AppConfig.CPF_LENGTH;
    }

    public boolean isValidPhone(String phone){
        return phone != null && phone.length() == AppConfig.PHONE_LENGTH;
    }
}

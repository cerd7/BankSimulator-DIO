package org.cerd.bank.validator;

import org.cerd.bank.config.AppConfig;
import org.cerd.bank.exception.InvalidCpfException;

public class CpfValidator{

    public void validate(String cpf) throws InvalidCpfException{
        if(!isValidFormat(cpf)){
            throw new InvalidCpfException("CPF must have exactly 11 digits");
        }
        if (isAllSameDigit(cpf)) {
            throw new InvalidCpfException("CPF cannot have all same digits");
        }
        if (!hasValidCheckDigits(cpf)) {
            throw new InvalidCpfException("CPF has invalid check digits");
        }
    }

    public boolean isValid(String cpf){
        try{
            validate(cpf);
            return true;
        }catch(InvalidCpfException e){
            return false;
        }
    }

    private boolean isValidFormat(String cpf){
        return cpf != null &&
        cpf.length() == AppConfig.CPF_LENGTH &&
        cpf.matches("\\d{11}");
    }

    private boolean isAllSameDigit(String cpf){
        return cpf.matches("(\\d)\\1{10}");
    }

    private boolean hasValidCheckDigits(String cpf){
        int [] digits = cpf.chars().map(c -> c - '0').toArray();

        int sum1 = 0;
        for(int i = 0; i < 9; i++){
            sum1 += digits[i] * (10-i);
        }
        int firstVerifier = 11 - (sum1 % 11);
        if(firstVerifier >= 10) firstVerifier=0;
        if(firstVerifier != digits[9]) return false;

        int sum2 = 0;
        for(int i = 0; i < 10; i++){
            sum2 += digits[i] * (11 - i);
        }
        int secondVerifier = 11 - (sum2 % 11);
        if(secondVerifier >= 10) secondVerifier = 0;

        return secondVerifier == digits[10];
        
    }
}
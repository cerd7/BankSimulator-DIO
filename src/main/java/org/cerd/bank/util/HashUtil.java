package org.cerd.bank.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {
    
    public String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(encodedHash);
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    public boolean validatePassword(String rawPassword, String storeHash){
        return hashPassword(rawPassword).equals(storeHash);
    }
}

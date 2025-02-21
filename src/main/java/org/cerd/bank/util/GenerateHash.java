package org.cerd.bank.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class GenerateHash {
    public GenerateHash() {
    }

    public String hashGenerate(String name, String cpf) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = name + cpf;
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error to generate hash:" + e.getMessage());
        }
    }
}

package org.cerd.bank.util;

import java.util.Random;

public class AccountIdGenerator {
    private final Random random = new Random();

    public String generate(){
        int number = 1000 + random .nextInt(1000);
        return String.valueOf(number);
    }
}

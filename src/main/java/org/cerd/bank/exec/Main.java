package org.cerd.bank.exec;


import org.cerd.bank.operations.account.user.User;
import org.cerd.bank.operations.validation.ValidateUser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        User user = new User();
        Scanner scan = new Scanner(System.in);

        String name = scan.nextLine();
        System.out.println(name);
        String cpf = scan.nextLine();
        System.out.println(cpf);

        user.setName(name);
        user.setCpf(cpf);

        ValidateUser validateUser = new ValidateUser();
        validateUser.validate(name, cpf);
    }
}
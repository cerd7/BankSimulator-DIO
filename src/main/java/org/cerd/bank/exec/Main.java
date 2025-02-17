package org.cerd.bank.exec;


import org.cerd.bank.operations.account.user.User;
import org.cerd.bank.operations.account.user.UserRepository;
import org.cerd.bank.operations.noncustomer.CreateAccount;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        User user = new User();
        CreateAccount createAccount = new CreateAccount();
        UserRepository userRepository = new UserRepository();

        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        System.out.println(name);
        Integer age = scan.nextInt();
        System.out.println(age);
        String cellPhone = scan.nextLine();
        System.out.println(cellPhone);
        String cpf = scan.nextLine();
        System.out.println(cpf);

        user.setName(name);
        user.setAge(age);
        user.setCellPhone(cellPhone);
        user.setCpf(cpf);

        createAccount.createNewAccount(name, age, cellPhone,cpf);
        userRepository.readUser();
    }
}
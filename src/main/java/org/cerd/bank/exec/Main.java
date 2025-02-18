package org.cerd.bank.exec;


import org.cerd.bank.operations.account.user.User;
import org.cerd.bank.operations.account.user.UserRepository;
import org.cerd.bank.operations.costumer.functionality.Deposit;
import org.cerd.bank.operations.noncustomer.CreateAccount;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {
        User user = new User();
        CreateAccount createAccount = new CreateAccount();
        UserRepository userRepository = new UserRepository();
        Deposit deposit = new Deposit();

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

        //createAccount.createNewAccount(name, age, cellPhone,cpf);
        Deposit.newDeposit("12345698708", (double) 700.0);
        userRepository.readUser();
    }
}
package org.cerd.bank.controller;

import org.cerd.bank.service.AccountService;

import java.util.Scanner;

public class UserController extends AccountService {
    private final Scanner scannerOperations;
    private boolean checkCondition = true;
    private int receiveOptional;
    String receiveCpf;

    public UserController() {
        scannerOperations = new Scanner(System.in);
        receiveUser();
    }

    private void receiveUser() {
        System.out.println("Welcome to Cerd Bank...");
        System.out.println("Today is your first time? Press 1 for YES or 2 for NO");
        while (checkCondition) {
            Integer receiveOptional = scannerOperations.nextInt();
            switch (receiveOptional) {
                case 1:
                    System.out.println("Below you can see more about us:");
                    System.out.println("**--------------------------------------------------------------------------------------------------------**");
                    System.out.println("Our bank is amid at young people who have started theis adult life and are looking for a place to save their money and invest it.");
                    System.out.println("**--------------------------------------------------------------------------------------------------------**");
                    if (twoFactorValidate()) {
                        requestCreateAccount();
                    }
                    break;
                case 2:
                    System.out.println("Very nice, so I believe you have an account. Enter your CPF so we can check it in the system:");
                    if (twoFactorValidate()) {
                        requestCreateAccount();
                    }
                    break;
                default:
                    System.out.println("The value you entered is incompatible... Please try again with 1 or 2:");
                    continue;
            }
        }
    }

    //Atribuir essa função para a classe validateUtil
    private boolean twoFactorValidate() {
        System.out.println("As a precaution, enter your CPF to validate that the account exists in the system:");
        while (checkCondition) {
            receiveCpf = scannerOperations.next();
            if (receiveCpf.length() != 11) {
                System.out.println("The CPF entered does not contain the required number of characters... try again:");
            } else {
                if (validateCpf(receiveCpf)) {
                    System.out.println("Identify an account with this CPF... do you remember your PIN?");
                    checkCondition = false;
                    return false;
                } else {
                    System.out.println("User not found... You need to create an account!");
                    break;
                }
            }
        }
        return true;
    }

    private void requestCreateAccount() {
        System.out.println("Would you like to create an account? Press 1 for YES or 2 for NO:");
        while (checkCondition) {
            receiveOptional = scannerOperations.nextInt();
            switch (receiveOptional) {
                case 1:
                    System.out.println("Please enter your information:");
                    System.out.println("Name:");
                    String name = scannerOperations.next();
                    System.out.println("Age:");
                    int age = Integer.parseInt(scannerOperations.next());
                    System.out.println("CPF:");
                    String cpf = scannerOperations.next();
                    System.out.println("Cell Phone:");
                    String cellphone = scannerOperations.next();
                    if (name != null && age != ' ' && cpf != null && cellphone != null) {
                        createAccount(name, age, cpf, cellphone);
                        checkCondition = false;
                    } else {
                        System.out.println("Invalid values... start the process again.");
                    }
                    break;
                case 2:
                    System.out.println("Right, so we end up here... Have a nice day!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("The value you entered is incompatible... Please try again");
                    break;
            }
        }
    }
}

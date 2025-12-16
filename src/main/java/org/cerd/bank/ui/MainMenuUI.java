package org.cerd.bank.ui;

import java.util.Scanner;

import org.cerd.bank.model.User;

public class MainMenuUI {
    private final Scanner scanner;

    public MainMenuUI(){
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome(){
        System.out.println("Welcome to CERD BANK...");
    }

    public int getFirstTimeOption(){
        System.out.println("Is this your firts time? Press 1 for YES or 2 for NO");
        return readValidOption(1,2);
    }

    public void showBankInfo() {
        System.out.println("**====================================================**");
        System.out.println("""
            Our bank is aimed at young people who have started
            their adult life and are looking for a place to save
            their money and invest it.
            """);
        System.out.println("**====================================================**");
    }

    public boolean confirmCreateAccount(){
        System.out.println("Would you like to create an account? Press 1 for YES or 2 for NO:");
        return readValidOption(1,2) == 1;
    }

    public User collectionUserData(){
        User user = new User();
        System.out.println("Name: ");
        user.setName(scanner.nextLine());
        System.out.println("Age: ");
        user.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.println("CPF: ");
        user.setCpf(scanner.nextLine());
        System.out.println("Cell Phone: ");
        user.setCellPhone(scanner.nextLine());
        return user;
    }

    private int readValidOption(int min, int max){
        while(true){
            try{
                int option = Integer.parseInt(scanner.nextLine());
                if (option>=min && option<=max) {
                    return option;
                }
                System.out.println("\"Please enter a value between %d and %d:%n\", min, max");
            }catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}

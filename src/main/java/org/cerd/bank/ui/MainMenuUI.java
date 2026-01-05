package org.cerd.bank.ui;

import java.util.Scanner;

public class MainMenuUI implements AutoCloseable{
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

    public UserDataDTO collectionUserData(){
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Age: ");
        int age = readValidOption(18, 120);

        System.out.println("CPF:");
        String cpf = scanner.nextLine();

        System.out.println("Phone: ");
        String phone = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        return new UserDataDTO(name, age, cpf, phone, password);
    }

    private int readValidOption(int min, int max){
        while(true){
            try{
                int option = Integer.parseInt(scanner.nextLine());
                if (option>=min && option<=max) {
                    return option;
                }
                System.out.printf("\"Please enter a value between %d and %d:%n\", min, max");
            }catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    @Override
    public void close(){
        scanner.close();
    }

    public record UserDataDTO(
        String name, 
        int age, 
        String cpf, 
        String phone, 
        String password
    ){}
}

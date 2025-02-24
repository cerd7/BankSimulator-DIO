package org.cerd.bank.app;

import org.cerd.bank.controller.UserController;

public class Main {
    static UserController userController;
    public static void main(String[] args) {
        userController = new UserController();
    }
}
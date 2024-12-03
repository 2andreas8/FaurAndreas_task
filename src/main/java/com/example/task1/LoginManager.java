package com.example.task1;

import com.example.task1.domain.Owner;
import com.example.task1.service.OwnerService;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {

    private final OwnerService ownerService;
    private Owner loggedInOwner;

    public LoginManager(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public boolean login(String name, String password) {
        try {
            loggedInOwner = ownerService.authenticate(name, password);
            System.out.println("Login succesful! Welcome back, " + loggedInOwner.getName());
            return true;
        }catch (IllegalArgumentException e) {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }

    public void logout() {
        loggedInOwner = null;
        System.out.println("You have been logged out.");
    }

    public Owner getLoggedInOwner() {
        return loggedInOwner;
    }

    public void setLoggedInOwner(Owner loggedInOwner) {
        this.loggedInOwner = loggedInOwner;
    }

    public void printMenu() {
        System.out.println("--- Menu ----");
        System.out.println("1. Login");
        System.out.println("2. Register -> will be implemented"); // TO DO
        System.out.println("3. Exit");
    }

    public void printLoggedInMenu() {
        System.out.println("--- Options ---");
        System.out.println("1. Update remaining effort");
        System.out.println(("2. Logout"));
    }
}

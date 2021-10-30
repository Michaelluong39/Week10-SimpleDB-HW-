package com.company.views;

import java.util.Scanner;

public class UserDataInput {

    public void startTitle() {
        System.out.println("Welcome to your movie database...\n");
    }

    public String yesNo() {
        System.out.print("Would you like to add a movie to the list? ('y' for yes, else exit program): ");
        Scanner input = new Scanner(System.in);

        return input.next();
    }

    public String addMovie(String a) {
        System.out.println("Please enter the movie's " + a + ": ");
        Scanner input = new Scanner(System.in);

        return input.next();
    }
}

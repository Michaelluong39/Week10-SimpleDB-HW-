package com.company.views;

import java.util.Scanner;

public class UserDataInput {

    public void startTitle() {
        System.out.println("Welcome to your movie database...\n");
    }

    public String yesNo() {
        System.out.print("What would you like to do? (a=add movie, s=search movies): ");
        Scanner input = new Scanner(System.in);

        return input.next();
    }

    public String addMovie(String a) {
        System.out.println("Please enter the movie's " + a + ": ");
        Scanner input = new Scanner(System.in);

        return input.next();
    }

    public String searchMovies() {
        System.out.println("Enter the title you want to search for(case sensitive): ");
        Scanner input = new Scanner(System.in);

        return input.next();
    }
}

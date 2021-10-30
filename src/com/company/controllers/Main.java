package com.company.controllers;

import com.company.views.UserDataInput;
import com.company.models.Movie;

import java.util.ArrayList;

public class Main {
    public static UserDataInput view;
    public static String loop;
    public static String title;
    public static String year;
    public static String rate;

    public static void main(String[] args) {
        view = new UserDataInput();
        int add = 1;
        DBConnect db = new DBConnect("movies.db");
        db.createNewDatabase();
        db.addTables();
        view.startTitle();

        ArrayList<Movie> theMovies = db.getData();
        for(Movie movie : theMovies){
            System.out.println(movie.toString());
        }

        while(add == 1) {
            loop = view.yesNo();
            if (loop.equals("y")) {
                title = view.addMovie("title");
                year = view.addMovie("release year");
                rate = view.addMovie("maturity rating");
                db.addData(title, year, rate);
            }else {
                break;
            }
            }

        theMovies = db.getData();
        for(Movie movie : theMovies){
            System.out.println(movie.toString());
        }



    }
}

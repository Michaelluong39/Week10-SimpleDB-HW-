package com.company.controllers;

import com.company.models.Movie;
import com.company.views.UserDataInput;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    private String dbName;
    private String url;
    public static UserDataInput ud;

    public DBConnect(String dbName) {
        this.dbName = dbName;
        this.url = "jdbc:sqlite:C:/sqlite/db/" + dbName;
    }

    public void createNewDatabase() {

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) { //check if DB exist, if not, create it
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTables(){

        String sql = "CREATE TABLE IF NOT EXISTS movies (\n"
                + "	id integer PRIMARY KEY,\n" //table title, table type, primary or not null
                + "	title text NOT NULL,\n"
                + "	releaseDate text NOT NULL,\n"
                + " rating text NOT NULL\n"
                + ");";

        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Tables added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addData(String title, String releaseDate, String rating){

        String sql = "INSERT INTO movies(title, releaseDate, rating) VALUES ('" + title + "', '" + releaseDate + "', '" + rating + "');";
        System.out.println(sql); //(sql)prints out sql to display error
        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Data added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchData() {
        ud = new UserDataInput();
        String search = ud.searchMovies();
        String sql = "SELECT title, releaseDate, rating FROM movies WHERE title = '" + search + "' ";
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement statement = conn.createStatement();
            ResultSet movies = statement.executeQuery(sql);
            while(movies.next()) {
                String title = movies.getString("title");
                String releaseDate = movies.getString("releaseDate");
                String rating = movies.getString("rating");
                System.out.println("Movie found:\nTitle: " + title + " released on "  + releaseDate + " rated " + rating);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> getData(){
        String sql = "SELECT id, title, releaseDate, rating FROM movies";
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            ResultSet movies = statement.executeQuery(sql);
            while(movies.next()) //.next loops until it runs out of data
            {
                int id = movies.getInt("id");
                String title = movies.getString("title");
                String releaseDate = movies.getString("releaseDate");
                String rating = movies.getString("rating");
                Movie movie = new Movie(id, title, releaseDate, rating);

                movieList.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }
}

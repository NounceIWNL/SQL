package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteJDBC {
    private static Statement statement = null;

    public static void main(String[] args) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:SqliteJDBC/src/main/resources/employers.db");
        ) {
            statement = connection.createStatement();
            create();
            readAll();
            update();
            delete();
            System.out.println();
            readAll();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void readAll() throws SQLException {

        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        //Список всех служащих
        ResultSet rs = statement.executeQuery("select name, salary from employer");
        while (rs.next())
            System.out.printf("name: %s, salary: %f%n", rs.getString("name"), rs.getFloat("salary"));

    }

    public static void create() throws SQLException {

        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        String sql = "INSERT INTO employer(name, salary) VALUES ('Zara', 100)";
        statement.executeUpdate(sql);


    }

    public static void update() throws SQLException {

        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        String sql = "UPDATE employer SET salary = 200 WHERE name='Zara'";
        statement.executeUpdate(sql);


    }

    public static void delete() throws SQLException {

        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        String sql = "delete from employer WHERE name='Zara'";
        statement.executeUpdate(sql);


    }
}
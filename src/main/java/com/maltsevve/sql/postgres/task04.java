package com.maltsevve.sql.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class task04 {
    public static void main(String[] args) {
        DataBaseConnector dataBaseConnector = new DataBaseConnector(createTable(), request(), drop());

        dataBaseConnector.createTable();

        ResultSet resultSet = dataBaseConnector.sendRequest();

        System.out.println("Duplicate emails: ");

        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dataBaseConnector.dropTable();
    }

    private static String createTable() {
        return """
                Create table If Not Exists Person (Id int, Email varchar(255));
                Truncate table Person;
                insert into Person (Id, Email) values ('1', 'a@b.com');
                insert into Person (Id, Email) values ('2', 'c@b.com');
                insert into Person (Id, Email) values ('3', 'a@b.com');
                insert into Person (Id, Email) values ('4', 'c@b.com');
                                """;
    }

    private static String request() {
        return """
                SELECT email
                FROM person
                GROUP BY email
                HAVING COUNT(email) >= 2
                """;
    }

    private static String drop() {
        return """
                DROP TABLE Person;
                """;
    }
}

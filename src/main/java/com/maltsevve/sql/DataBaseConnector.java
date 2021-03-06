package com.maltsevve.sql;

import java.sql.*;

public class DataBaseConnector {
    public static void main(String[] args) {
        String request = """
                SELECT firstname, lastname, city, state
                FROM person
                LEFT JOIN address a on person.personid = a.personid
                """;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "q1w2e3r4");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + ": " + resultSet.getString("firstname") + " " +
                        resultSet.getString("lastname") + " - " + resultSet.getString("city")
                        + ", " + resultSet.getString("state"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}

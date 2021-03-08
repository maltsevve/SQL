package com.maltsevve.sql.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class task02 {
    public static void main(String[] args) {
        DataBaseConnector dataBaseConnector = new DataBaseConnector(createTable(), request(), drop());

        dataBaseConnector.createTable();

        ResultSet resultSet = dataBaseConnector.sendRequest();

        try {
            while (resultSet.next()) {
                System.out.println("Second highest salary: " +
                        resultSet.getString("salary"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dataBaseConnector.dropTable();
    }

    private static String createTable() {
        return """
                CREATE TABLE IF NOT EXISTS Employee
                (
                    Id     int,
                    Salary int
                );
                TRUNCATE TABLE Employee;
                INSERT INTO Employee (Id, Salary)
                VALUES ('1', '100');
                INSERT INTO Employee (Id, Salary)
                VALUES ('2', '200');
                INSERT INTO Employee (Id, Salary)
                VALUES ('3', '300');
                                """;
    }

    private static String request() {
        return """
                SELECT *
                FROM employee
                WHERE salary < (SELECT MAX(salary) FROM employee)
                ORDER BY salary DESC
                LIMIT 1;
                """;
    }

    private static String drop() {
        return """
                DROP TABLE Employee;
                """;
    }
}

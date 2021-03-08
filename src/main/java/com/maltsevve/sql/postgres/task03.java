package com.maltsevve.sql.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class task03 {
    public static void main(String[] args) {
        DataBaseConnector dataBaseConnector = new DataBaseConnector(createTable(), request(), drop());

        dataBaseConnector.createTable();

        ResultSet resultSet = dataBaseConnector.sendRequest();

        try {
            while (resultSet.next()) {
                System.out.println("Employees who earn more than their managers: " +
                        resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dataBaseConnector.dropTable();
    }

    private static String createTable() {
        return """
                Create table If Not Exists Employee
                (
                    Id        int,
                    Name      varchar(255),
                    Salary    int,
                    ManagerId int
                );
                Truncate table Employee;
                insert into Employee (Id, Name, Salary, ManagerId)
                values ('1', 'Joe', '70000', '3');
                insert into Employee (Id, Name, Salary, ManagerId)
                values ('2', 'Henry', '80000', '4');
                insert into Employee (Id, Name, Salary, ManagerId)
                values ('3', 'Sam', '60000', null);
                insert into Employee (Id, Name, Salary, ManagerId)
                values ('4', 'Max', '90000', null);
                                """;
    }

    private static String request() {
        return """
                SELECT name
                FROM employee
                WHERE managerid IS NULL
                  AND salary > (SELECT MAX(salary)
                                FROM employee
                                WHERE managerid NOTNULL)
                """;
    }

    private static String drop() {
        return """
                DROP TABLE Employee;
                """;
    }
}

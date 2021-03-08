package com.maltsevve.sql.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class task05 {
    public static void main(String[] args) {
        DataBaseConnector dataBaseConnector = new DataBaseConnector(createTable(), request(), drop());

        dataBaseConnector.createTable();

        ResultSet resultSet = dataBaseConnector.sendRequest();

        System.out.println("Customers who never order anything: ");

        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dataBaseConnector.dropTable();
    }

    private static String createTable() {
        return """
                Create table If Not Exists Customers (Id int, Name varchar(255));
                Create table If Not Exists Orders (Id int, CustomerId int);
                Truncate table Customers;
                insert into Customers (Id, Name) values ('1', 'Joe');
                insert into Customers (Id, Name) values ('2', 'Henry');
                insert into Customers (Id, Name) values ('3', 'Sam');
                insert into Customers (Id, Name) values ('4', 'Max');
                Truncate table Orders;
                insert into Orders (Id, CustomerId) values ('1', '3');
                insert into Orders (Id, CustomerId) values ('2', '1');
                                """;
    }

    private static String request() {
        return """
                SELECT name
                FROM customers
                LEFT JOIN orders
                ON customers.id = CustomerId
                WHERE CustomerId IS NULL
                """;
    }

    private static String drop() {
        return """
                DROP TABLE Customers;
                DROP TABLE Orders;
                """;
    }
}

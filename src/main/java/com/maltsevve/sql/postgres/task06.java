package com.maltsevve.sql.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class task06 {
    public static void main(String[] args) {
        DataBaseConnector dataBaseConnector = new DataBaseConnector(createTable(), request(), drop());

        dataBaseConnector.createTable();

        ResultSet resultSet = dataBaseConnector.sendRequest();

        System.out.println("Classes which have more than or equal to 5 students: ");

        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("class"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dataBaseConnector.dropTable();
    }

    private static String createTable() {
        return """
                Create table If Not Exists courses (student varchar(255), class varchar(255));
                Truncate table courses;
                insert into courses (student, class) values ('A', 'Math');
                insert into courses (student, class) values ('B', 'English');
                insert into courses (student, class) values ('C', 'Math');
                insert into courses (student, class) values ('D', 'Biology');
                insert into courses (student, class) values ('E', 'Math');
                insert into courses (student, class) values ('F', 'Computer');
                insert into courses (student, class) values ('G', 'Math');
                insert into courses (student, class) values ('H', 'Math');
                insert into courses (student, class) values ('I', 'Math');
                                """;
    }

    private static String request() {
        return """
                SELECT class
                FROM courses
                GROUP BY class
                HAVING COUNT(class) >= 5
                """;
    }

    private static String drop() {
        return """
                DROP TABLE Courses;
                """;
    }
}

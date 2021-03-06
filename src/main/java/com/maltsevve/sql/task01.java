package com.maltsevve.sql;

public class task01 {
    public static void main(String[] args) {
        DataBaseConnector dataBaseConnector = new DataBaseConnector(createTable(), request(), drop());

//        dataBaseConnector.createTable();

        dataBaseConnector.printRequest(dataBaseConnector.sendRequest());

//        dataBaseConnector.dropTable();

    }

    private static String createTable() {
        return """
                   Create table Person
                   (
                       PersonId  int,
                       FirstName varchar(255),
                       LastName  varchar(255)
                   );
                   Create table Address
                   (
                       AddressId int,
                       PersonId  int,
                       City      varchar(255),
                       State     varchar(255)
                   );
                   Truncate table Person;
                   insert into Person (PersonId, LastName, FirstName)
                   values ('1', 'Wang', 'Allen');
                   insert into Person (PersonId, LastName, FirstName)
                   values ('2', 'John', 'Doe');
                   Truncate table Address;
                   insert into Address (AddressId, PersonId, City, State)
                   values ('1', '2', 'New York City', 'New York');
                """;
    }

    private static String request() {
        return """
                SELECT firstname, lastname, city, state
                FROM person
                LEFT JOIN address a on person.personid = a.personid
                """;
    }

    private static String drop() {
        return """
                DROP TABLE person;
                DROP TABLE address;
                """;
    }
}

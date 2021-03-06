package com.maltsevve.sql;

import lombok.*;

import java.sql.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class DataBaseConnector {
    private final @NonNull String create;
    private final @NonNull String request;
    private final @NonNull String drop;

    private Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "q1w2e3r4");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public void createTable() {
        try {
            Statement statement = connect().createStatement();
            statement.executeQuery(create);
            connect().endRequest();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet sendRequest() {
        ResultSet resultSet = null;
        try {
            Statement statement = connect().createStatement();
            statement.executeQuery(request);
            resultSet = statement.executeQuery(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    public void printRequest(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + ": " + resultSet.getString("firstname") + " " +
                        resultSet.getString("lastname") + " - " + resultSet.getString("city")
                        + ", " + resultSet.getString("state"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            Statement statement = connect().createStatement();
            statement.executeQuery(drop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

package edu.folhaPgto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DB_JDBC_URI =
        "jdbc:mariadb://localhost:3306/folha_pagamento?allowPublicKeyRetrieval=true&useSSL=false";

    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
            DB_JDBC_URI,
            DB_USER,
            DB_PASS
        );
    }
}

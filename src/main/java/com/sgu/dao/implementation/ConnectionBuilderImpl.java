package com.sgu.dao.implementation;

import com.sgu.dao.ConnectionBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionBuilderImpl implements ConnectionBuilder {

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/sguOopDatabase";
    private static final String DEFAULT_LOGIN_AND_PASSWORD = "postgres";

    private Connection connection;

    public ConnectionBuilderImpl() {
        try {
            connection = DriverManager.getConnection(URL, DEFAULT_LOGIN_AND_PASSWORD, DEFAULT_LOGIN_AND_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

}

package com.ilyamur.topaz.sqltool;

import org.springframework.jdbc.datasource.AbstractDataSource;

import java.sql.Connection;

public class ConnectionWrappingDataSource extends AbstractDataSource {

    private Connection connection;

    public ConnectionWrappingDataSource(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) {
        return connection;
    }
}

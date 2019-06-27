package com.ilyamur.topaz.sqltool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configurable
public class Transaction implements AutoCloseable {

    @Autowired
    private EntityProvider entityProvider;

    @Autowired
    private SqlParser sqlParser;

    private DataSource dataSource;

    public Transaction(Connection connection) {
        dataSource = new ConnectionWrappingDataSource(connection);
    }

    public Execution execute(String sql, Param... params) {
        ParseResult parseResult = sqlParser.parse(sql, params);
        return new Execution(mapper -> {
            try {
                return entityProvider.getEntities(dataSource, mapper, parseResult.getSql(), parseResult.getParams());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void close() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            // todo log error message
            return; // ignore
        }
        try {
            connection.rollback();
        } catch (Exception e) {
            // todo log error message
        }
        try {
            connection.close();
        } catch (Exception e) {
            // todo log error message
        }
    }
}

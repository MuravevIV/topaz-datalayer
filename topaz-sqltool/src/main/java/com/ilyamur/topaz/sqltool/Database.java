package com.ilyamur.topaz.sqltool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

@Component
@Configurable
public class Database {

    private DataSource dataSource;

    @Autowired
    private EntityProvider entityProvider;

    @Autowired
    private SqlParser sqlParser;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Execution execute(String sql, Param ... params) {
        ParseResult parseResult = sqlParser.parse(sql, params);
        return new Execution(mapper -> {
            try {
                return entityProvider.getEntities(dataSource, mapper, parseResult.getSql(), parseResult.getParams());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

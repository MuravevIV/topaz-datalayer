package com.ilyamur.topaz.sqltool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

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

    public UpdateResult update(String sql, Param... params) {
        ParseResult parseResult = sqlParser.parse(sql, params);
        try {
            int updateCount = entityProvider.updateEntities(dataSource, parseResult.getSql(), parseResult.getParams());
            return new UpdateResult(updateCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

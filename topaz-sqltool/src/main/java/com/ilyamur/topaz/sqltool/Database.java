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

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Execution execute(String sql, Param ... params) throws SQLException {

        Function<Mapper<?>, List<?>> mappingFunction = mapper -> {
            try {
                return entityProvider.getEntities(dataSource, sql, mapper);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        return new Execution(mappingFunction);
    }
}

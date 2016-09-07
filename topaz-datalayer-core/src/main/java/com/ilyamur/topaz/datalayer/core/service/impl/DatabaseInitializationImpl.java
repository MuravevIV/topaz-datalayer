package com.ilyamur.topaz.datalayer.core.service.impl;

import com.ilyamur.topaz.datalayer.core.service.DatabaseInitialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseInitializationImpl implements DatabaseInitialization {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseInitializationImpl.class);

    private static final String DB_SCHEMA_SQL = "db/schema.sql";

    @Autowired
    private DataSource dataSource;

    @Override
    public void apply() {
        try {
            createSchema();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createSchema() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource(DB_SCHEMA_SQL));
            populator.populate(connection);
        }
    }
}

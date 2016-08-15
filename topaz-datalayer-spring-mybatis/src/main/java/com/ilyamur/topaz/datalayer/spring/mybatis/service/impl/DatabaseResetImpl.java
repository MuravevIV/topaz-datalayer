package com.ilyamur.topaz.datalayer.spring.mybatis.service.impl;

import com.ilyamur.topaz.datalayer.spring.mybatis.ApplicationProfile;
import com.ilyamur.topaz.datalayer.spring.mybatis.service.DatabaseReset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@Profile(ApplicationProfile.TESTING)
public class DatabaseResetImpl implements DatabaseReset {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseResetImpl.class);

    private static final String DB_SCHEMA_SQL = "db/schema.sql";
    private static final String DB_INITIAL_SQL = "db/initial.sql";

    @Autowired
    private DataSource dataSource;

    @Override
    public void apply() {
        try {
            cleanup();
            populate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void cleanup() throws SQLException {
        try (Statement stmt = dataSource.getConnection().createStatement()) {
            stmt.executeUpdate("DROP SCHEMA PUBLIC CASCADE");
        }
    }

    private void populate() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource(DB_SCHEMA_SQL));
            populator.addScript(new ClassPathResource(DB_INITIAL_SQL));
            populator.populate(connection);
        }
    }
}

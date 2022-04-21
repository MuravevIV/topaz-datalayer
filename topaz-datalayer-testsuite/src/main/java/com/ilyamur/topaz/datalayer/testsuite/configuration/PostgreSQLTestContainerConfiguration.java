package com.ilyamur.topaz.datalayer.testsuite.configuration;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
public class PostgreSQLTestContainerConfiguration {

    @Bean
    public DataSource dataSource() {

        String databaseName = "integration-tests-db";
        String username = "username";
        String password = "password";

        PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
                .withDatabaseName(databaseName)
                .withUsername(username)
                .withPassword(password);

        postgreSQLContainer.start();

        String jdbcUrl = postgreSQLContainer.getJdbcUrl();

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}

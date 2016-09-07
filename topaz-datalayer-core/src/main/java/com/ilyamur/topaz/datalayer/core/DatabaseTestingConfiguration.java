package com.ilyamur.topaz.datalayer.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Profile(ApplicationProfile.TESTING)
public class DatabaseTestingConfiguration {

    @Bean
    public DataSource dataSource() {
        return (new EmbeddedDatabaseBuilder())
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }
}

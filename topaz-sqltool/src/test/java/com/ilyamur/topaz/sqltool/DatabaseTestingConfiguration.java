package com.ilyamur.topaz.sqltool;

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
                .addScript("classpath:db/schema.sql")
                .addScript("classpath:db/dual.sql")
                .addScript("classpath:db/bills.sql")
                .addScript("classpath:db/test_tables_schema.sql")
                .build();
    }
}

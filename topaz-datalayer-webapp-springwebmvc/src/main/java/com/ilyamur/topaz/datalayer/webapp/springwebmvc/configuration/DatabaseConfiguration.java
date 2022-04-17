package com.ilyamur.topaz.datalayer.webapp.springwebmvc.configuration;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Profile(ApplicationProfile.DEVELOP)
public class DatabaseConfiguration {

    @Bean
    @Primary
    public DataSource dataSource() {
        return (new EmbeddedDatabaseBuilder())
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }
}

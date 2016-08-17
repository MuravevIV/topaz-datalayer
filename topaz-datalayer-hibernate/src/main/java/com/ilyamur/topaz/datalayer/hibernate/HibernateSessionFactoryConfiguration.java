package com.ilyamur.topaz.datalayer.hibernate;

import com.ilyamur.topaz.datalayer.core.DataSourceConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;

import java.util.Properties;

@Configuration
@Import({
        DataSourceConfiguration.class
})
public class HibernateSessionFactoryConfiguration {

    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.HSQLDialect";
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_GENERATE_STATISTICS = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "create";
    private static final String HIBERNATE_PACKAGES_TO_SCAN = "com.ilyamur.topaz.datalayer.core.entity";

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(HIBERNATE_PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
        hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        hibernateProperties.put("hibernate.generate_statistics", HIBERNATE_GENERATE_STATISTICS);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        return sessionFactoryBean;
    }
}

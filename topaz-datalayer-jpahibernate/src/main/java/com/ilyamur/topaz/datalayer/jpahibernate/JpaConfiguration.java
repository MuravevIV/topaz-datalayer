package com.ilyamur.topaz.datalayer.jpahibernate;

import com.ilyamur.topaz.datalayer.core.DatabaseTestingConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

import java.util.Properties;

@Import({
        DatabaseTestingConfiguration.class
})
public class JpaConfiguration {

    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.HSQLDialect";
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_GENERATE_STATISTICS = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "create";
    private static final String HIBERNATE_PACKAGES_TO_SCAN = "com.ilyamur.topaz.datalayer.core.entity";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(HIBERNATE_PACKAGES_TO_SCAN);
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
        additionalProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        additionalProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        additionalProperties.put("hibernate.generate_statistics", HIBERNATE_GENERATE_STATISTICS);
        entityManagerFactory.setJpaProperties(additionalProperties);
        return entityManagerFactory;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

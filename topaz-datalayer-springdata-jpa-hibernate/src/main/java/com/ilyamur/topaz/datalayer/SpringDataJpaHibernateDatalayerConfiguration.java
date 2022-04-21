package com.ilyamur.topaz.datalayer;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories
@Import(CoreConfiguration.class)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class SpringDataJpaHibernateDatalayerConfiguration {

    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQL82Dialect";
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_GENERATE_STATISTICS = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "create";
    private static final String HIBERNATE_PACKAGES_TO_SCAN = "com.ilyamur.topaz.datalayer.core.entity";

    @Bean
    public DataSource dataSource() {
        throw new IllegalStateException("Please define 'javax.sql.DataSource' bean explicitly " +
                "in one of the modules to override this exception");
    }

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

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        return transactionManager;
    }
}

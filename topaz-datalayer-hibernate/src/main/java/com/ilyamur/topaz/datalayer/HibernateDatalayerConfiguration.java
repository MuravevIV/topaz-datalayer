package com.ilyamur.topaz.datalayer;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({CoreConfiguration.class})
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class HibernateDatalayerConfiguration {

    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQL82Dialect";
    public static final String HIBERNATE_SHOW_SQL = "true";
    public static final String HIBERNATE_GENERATE_STATISTICS = "true";
    public static final String HIBERNATE_HBM2DDL_AUTO = "create";
    public static final String HIBERNATE_PACKAGES_TO_SCAN = "com.ilyamur.topaz.datalayer.core.entity";

    @Bean
    public DataSource dataSource() {
        throw new IllegalStateException("Please define 'javax.sql.DataSource' bean explicitly " +
                "in one of the modules to override this exception");
    }

    @Bean
    public FactoryBean<SessionFactory> localSessionFactoryBean(DataSource dataSource) {
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

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        // todo - investigate
        // transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }
}

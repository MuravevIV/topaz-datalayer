package com.ilyamur.topaz.datalayer.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
public class HibernateTransactionConfiguration {

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        // transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }
}

package com.ilyamur.topaz.datalayer.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@Import({
        HibernateSessionFactoryConfiguration.class
})
public class HibernateTransactionConfiguration {

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        // transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }
}

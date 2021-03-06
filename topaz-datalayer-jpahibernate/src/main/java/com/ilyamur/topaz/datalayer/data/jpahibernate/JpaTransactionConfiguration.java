package com.ilyamur.topaz.datalayer.data.jpahibernate;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@Import({
        JpaConfiguration.class
})
public class JpaTransactionConfiguration {

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        // transactionManager.setJpaDialect(new HibernateJpaDialect());
        // transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }
}

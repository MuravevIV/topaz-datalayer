package com.ilyamur.topaz.datalayer.core;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class TestConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new TransactionManagerStub();
    }
}

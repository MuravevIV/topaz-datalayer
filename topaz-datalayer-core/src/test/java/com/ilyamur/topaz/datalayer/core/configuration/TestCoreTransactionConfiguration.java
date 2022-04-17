package com.ilyamur.topaz.datalayer.core.configuration;

import com.ilyamur.topaz.datalayer.core.TestingTransactionManager;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class TestCoreTransactionConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new TestingTransactionManager();
    }
}

package com.ilyamur.topaz.datalayer;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@Import(CoreConfiguration.class)
@EnableTransactionManagement // (mode = AdviceMode.ASPECTJ)
public class HibernateDatalayerConfiguration {
}

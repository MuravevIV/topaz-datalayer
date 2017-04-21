package com.ilyamur.topaz.datalayer.hibernate;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        CoreConfiguration.class,
        HibernateConfiguration.class,
        HibernateTransactionConfiguration.class
})
public class HibernateDatalayerConfiguration {
}

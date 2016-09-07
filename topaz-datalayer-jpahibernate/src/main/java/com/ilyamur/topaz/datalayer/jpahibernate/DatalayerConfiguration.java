package com.ilyamur.topaz.datalayer.jpahibernate;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        CoreConfiguration.class,
        JpaConfiguration.class,
        JpaTransactionConfiguration.class
})
public class DatalayerConfiguration {
}

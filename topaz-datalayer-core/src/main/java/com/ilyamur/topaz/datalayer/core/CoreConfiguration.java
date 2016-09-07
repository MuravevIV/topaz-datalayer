package com.ilyamur.topaz.datalayer.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        AspectJConfiguration.class,
        DatabaseTestingConfiguration.class
})
public class CoreConfiguration {
}

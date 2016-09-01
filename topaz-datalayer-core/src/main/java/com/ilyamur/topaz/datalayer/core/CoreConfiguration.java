package com.ilyamur.topaz.datalayer.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
    // EntityWeavingConfiguration.class
})
public class CoreConfiguration {
}

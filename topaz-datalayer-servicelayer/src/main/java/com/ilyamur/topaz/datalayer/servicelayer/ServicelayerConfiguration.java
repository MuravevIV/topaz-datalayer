package com.ilyamur.topaz.datalayer.servicelayer;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        CoreConfiguration.class
})
public class ServicelayerConfiguration {
}

package com.ilyamur.topaz.datalayer;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(CoreConfiguration.class)
public class SpringDataJpaHibernateDatalayerConfiguration {
}

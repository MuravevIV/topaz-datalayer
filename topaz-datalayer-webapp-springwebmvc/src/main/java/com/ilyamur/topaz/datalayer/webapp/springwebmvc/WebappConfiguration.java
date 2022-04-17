package com.ilyamur.topaz.datalayer.webapp.springwebmvc;

import com.ilyamur.topaz.datalayer.SpringDataJpaHibernateDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.ServicelayerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
@Import({
        CoreConfiguration.class,
        SpringDataJpaHibernateDatalayerConfiguration.class,
        ServicelayerConfiguration.class
})
public class WebappConfiguration implements WebMvcConfigurer {
}

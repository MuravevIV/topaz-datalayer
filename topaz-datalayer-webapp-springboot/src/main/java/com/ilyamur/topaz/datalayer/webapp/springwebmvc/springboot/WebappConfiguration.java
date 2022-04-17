package com.ilyamur.topaz.datalayer.webapp.springwebmvc.springboot;

import com.ilyamur.topaz.datalayer.ServicelayerConfiguration;
import com.ilyamur.topaz.datalayer.SpringDataJpaHibernateDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreConfiguration.class,
        SpringDataJpaHibernateDatalayerConfiguration.class,
        ServicelayerConfiguration.class
})
public class WebappConfiguration {
}

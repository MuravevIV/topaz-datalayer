package com.ilyamur.topaz.datalayer.webapp;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.hibernate.HibernateDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan
@Import({
        CoreConfiguration.class,
        DatabaseConfiguration.class,
        HibernateDatalayerConfiguration.class,
        ServicelayerConfiguration.class,
        ThymeleafConfiguration.class
})
public class WebappConfiguration {
}

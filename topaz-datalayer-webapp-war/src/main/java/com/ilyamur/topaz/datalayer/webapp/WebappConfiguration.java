package com.ilyamur.topaz.datalayer.webapp;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.data.jpahibernate.DataJpaDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;
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
        DataJpaDatalayerConfiguration.class,
        ServicelayerConfiguration.class,
        ThymeleafConfiguration.class
})
public class WebappConfiguration implements WebMvcConfigurer {
}

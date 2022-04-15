package com.ilyamur.topaz.datalayer.webapp.configuration;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.data.jpahibernate.DataJpaDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreConfiguration.class,
        DataJpaDatalayerConfiguration.class,
        ServicelayerConfiguration.class
})
public class WebappConfiguration {
}

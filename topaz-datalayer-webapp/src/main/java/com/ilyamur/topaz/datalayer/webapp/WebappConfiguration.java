package com.ilyamur.topaz.datalayer.webapp;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.jpahibernate.DatalayerConfiguration;
import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Import;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan
@EnableLoadTimeWeaving
@Import({
        CoreConfiguration.class,
        DatabaseConfiguration.class,
        DatalayerConfiguration.class,
        ServicelayerConfiguration.class,
        ThymeleafConfiguration.class
})
public class WebappConfiguration {

    @Autowired
    private LoadTimeWeaver loadTimeWeaver;
}

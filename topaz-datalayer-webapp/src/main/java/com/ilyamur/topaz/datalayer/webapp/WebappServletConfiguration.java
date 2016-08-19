package com.ilyamur.topaz.datalayer.webapp;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.mybatis.DatalayerConfiguration;
import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan
@Import({
        CoreConfiguration.class,
        DatabaseConfiguration.class,
        DatalayerConfiguration.class,
        ServicelayerConfiguration.class,

        // WebappTransactionConfiguration.class,
        ThymeleafConfiguration.class
})
public class WebappServletConfiguration {
}

package com.ilyamur.topaz.datalayer.testsuite;

import com.ilyamur.topaz.datalayer.ServicelayerConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.configuration.PostgreSQLTestContainerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServicelayerConfiguration.class, PostgreSQLTestContainerConfiguration.class})
public class TestSuiteConfiguration {
}

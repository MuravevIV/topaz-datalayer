package com.ilyamur.topaz.datalayer.testsuite;

import com.ilyamur.topaz.datalayer.ServicelayerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(ServicelayerConfiguration.class)
public class TestSuiteConfiguration {
}

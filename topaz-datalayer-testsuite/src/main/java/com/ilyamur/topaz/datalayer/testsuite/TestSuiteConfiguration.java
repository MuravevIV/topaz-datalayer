package com.ilyamur.topaz.datalayer.testsuite;

import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({ServicelayerConfiguration.class, DatabaseTestingConfiguration.class})
public class TestSuiteConfiguration {
}

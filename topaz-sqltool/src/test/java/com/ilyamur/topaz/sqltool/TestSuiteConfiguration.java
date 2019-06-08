package com.ilyamur.topaz.sqltool;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({BaseConfiguration.class, DatabaseTestingConfiguration.class})
public class TestSuiteConfiguration {
}

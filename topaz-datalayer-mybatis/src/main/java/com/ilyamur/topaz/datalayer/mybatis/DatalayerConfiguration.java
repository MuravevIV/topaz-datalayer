package com.ilyamur.topaz.datalayer.mybatis;

import com.ilyamur.topaz.datalayer.core.CoreConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        CoreConfiguration.class,
        MybatisConfiguration.class,
        MybatisTransactionConfiguration.class
})
public class DatalayerConfiguration {
}

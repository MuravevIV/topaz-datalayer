package com.ilyamur.topaz.datalayer.hibernate;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        HibernateSessionFactoryConfiguration.class,
        HibernateStatisticsConfiguration.class
})
public class HibernateConfiguration {
}

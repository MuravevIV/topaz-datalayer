package com.ilyamur.topaz.datalayer.hibernate;

import org.springframework.context.annotation.Import;

@Import({
        HibernateSessionFactoryConfiguration.class,
        HibernateStatisticsConfiguration.class
})
public class HibernateConfiguration {
}

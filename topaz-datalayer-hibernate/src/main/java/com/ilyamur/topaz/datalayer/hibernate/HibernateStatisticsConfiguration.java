package com.ilyamur.topaz.datalayer.hibernate;

import com.ilyamur.topaz.datalayer.hibernate.util.HibernateStatisticsFactoryBean;

import com.google.common.collect.Maps;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import java.util.HashMap;

@Import({
        HibernateSessionFactoryConfiguration.class
})
public class HibernateStatisticsConfiguration {

    private static final String HIBERNATE_TYPE_STATISTICS = "Hibernate:type=statistics";

    @Bean
    public HibernateStatisticsFactoryBean hibernateStatisticsMBean(SessionFactory sessionFactory) {
        HibernateStatisticsFactoryBean bean = new HibernateStatisticsFactoryBean();
        bean.setSessionFactory(sessionFactory);
        return bean;
    }

    @Bean
    public MBeanServerFactoryBean mbeanServer() {
        MBeanServerFactoryBean bean = new MBeanServerFactoryBean();
        bean.setLocateExistingServerIfPossible(true);
        return bean;
    }

    @Bean
    public MBeanExporter jmxExporter(Statistics statistics) {
        MBeanExporter bean = new MBeanExporter();
        HashMap<String, Object> beans = Maps.newHashMap();
        beans.put(HIBERNATE_TYPE_STATISTICS, statistics);
        bean.setBeans(beans);
        return bean;
    }
}

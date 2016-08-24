package com.ilyamur.topaz.datalayer.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.instrument.classloading.LoadTimeWeaver;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
public class EntityWeavingConfiguration {

    @Autowired
    private LoadTimeWeaver loadTimeWeaver;

/*    @Bean
    public AnnotationTransactionAspect annotationTransactionAspect(PlatformTransactionManager transactionManager) {
        AnnotationTransactionAspect annotationTransactionAspect = Aspects.aspectOf(AnnotationTransactionAspect.class);
        annotationTransactionAspect.setTransactionManager(transactionManager);
        return annotationTransactionAspect;
    }*/
}

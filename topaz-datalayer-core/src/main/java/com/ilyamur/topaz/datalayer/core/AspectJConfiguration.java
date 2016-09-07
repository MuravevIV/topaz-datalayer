package com.ilyamur.topaz.datalayer.core;

import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

// @EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableSpringConfigured
class AspectJConfiguration {

/*    @Autowired
    private LoadTimeWeaver loadTimeWeaver;*/

/*    @Bean
    public AnnotationTransactionAspect annotationTransactionAspect(PlatformTransactionManager transactionManager) {
        AnnotationTransactionAspect annotationTransactionAspect = Aspects.aspectOf(AnnotationTransactionAspect.class);
        annotationTransactionAspect.setTransactionManager(transactionManager);
        return annotationTransactionAspect;
    }*/
}

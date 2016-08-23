package com.ilyamur.topaz.datalayer.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@EnableSpringConfigured
@EnableLoadTimeWeaving
public class EntityWeavingConfiguration {
}

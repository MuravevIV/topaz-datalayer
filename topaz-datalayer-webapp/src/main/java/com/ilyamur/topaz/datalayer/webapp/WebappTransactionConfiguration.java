package com.ilyamur.topaz.datalayer.webapp;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class WebappTransactionConfiguration {
}

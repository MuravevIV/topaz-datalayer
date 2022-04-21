package com.ilyamur.topaz.datalayer.repository;

import com.ilyamur.topaz.datalayer.SpringDataJpaHibernateDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.testsuite.TestSuiteConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.UserRepositoryTestSuite;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {SpringDataJpaHibernateDatalayerConfiguration.class, TestSuiteConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class SpringDataJpaHibernateUserRepositoryTest extends UserRepositoryTestSuite {
}

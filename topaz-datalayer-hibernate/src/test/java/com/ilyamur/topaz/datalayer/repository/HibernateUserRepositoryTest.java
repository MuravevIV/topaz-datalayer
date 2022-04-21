package com.ilyamur.topaz.datalayer.repository;

import com.ilyamur.topaz.datalayer.HibernateDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.testsuite.TestSuiteConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.UserRepositoryTestSuite;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {HibernateDatalayerConfiguration.class, TestSuiteConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class HibernateUserRepositoryTest extends UserRepositoryTestSuite {
}

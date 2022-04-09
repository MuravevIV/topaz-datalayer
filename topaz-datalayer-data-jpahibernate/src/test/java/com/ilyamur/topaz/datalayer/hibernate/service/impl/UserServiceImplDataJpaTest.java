package com.ilyamur.topaz.datalayer.hibernate.service.impl;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.data.jpahibernate.DataJpaDatalayerConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.TestSuiteConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.UserServiceImplTestSuite;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataJpaDatalayerConfiguration.class, TestSuiteConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserServiceImplDataJpaTest extends UserServiceImplTestSuite {
}

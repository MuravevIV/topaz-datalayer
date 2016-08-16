package com.ilyamur.topaz.datalayer.spring.hibernate.service.impl;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.spring.hibernate.ApplicationConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.UserServiceImplTestSuite;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserServiceImplTest extends UserServiceImplTestSuite {
}

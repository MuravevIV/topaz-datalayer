package com.ilyamur.topaz.datalayer.jpahibernate.service.impl;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.jpahibernate.ApplicationConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.TestSuiteConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.UserServiceImplTestSuite;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, TestSuiteConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserServiceImplJpaHibernateTest extends UserServiceImplTestSuite {

    // This cases doesn't work for JPA + Hibernate datalayer, because it doesn't support savepoints:

    @Override
    @Ignore
    @Test
    public void saveTwoUsersWithSameLoginSequentially_savesOnlyFirstUserAndThrowsLoginExistsException() throws LoginExistsException {
    }

    @Override
    @Ignore
    @Test
    public void saveTwoUsersWithSameLoginSimultaneously_doNotSaveAnyUserAndThrowsLoginExistsException() throws LoginExistsException {
    }

    @Override
    @Ignore
    @Test
    public void saveUsersInNestedExceptionScenario_savesNothing() throws LoginExistsException {
    }
}

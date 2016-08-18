package com.ilyamur.topaz.datalayer.mybatis.service.impl;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.mybatis.ApplicationConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.TestSuiteConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.UserServiceImplTestSuite;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, TestSuiteConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserServiceImplMybatisTest extends UserServiceImplTestSuite {
}

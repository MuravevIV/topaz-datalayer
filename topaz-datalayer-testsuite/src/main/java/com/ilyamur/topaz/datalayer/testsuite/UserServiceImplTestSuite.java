package com.ilyamur.topaz.datalayer.testsuite;

import com.ilyamur.topaz.datalayer.core.service.UserService;
import com.ilyamur.topaz.datalayer.core.service.DatabaseReset;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class UserServiceImplTestSuite {

    @Autowired
    private UserService userService;

    @Autowired
    private DatabaseReset databaseReset;

    @Before
    public void before() {
        databaseReset.apply();
    }

    @Test
    public void changeEmail() {
        userService.changeEmail(0L, "new@mail.com");
    }
}

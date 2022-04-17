package com.ilyamur.topaz.datalayer.testsuite;

import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import com.ilyamur.topaz.datalayer.core.service.DatabaseReset;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class UserRepositoryTestSuite {

    @Autowired
    private DatabaseReset databaseReset;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {
        databaseReset.apply();
    }

    @Test
    public void changeEmail() {
        // userService.changeEmail(0L, "new@mail.com");
        System.out.println("ok");
    }
}

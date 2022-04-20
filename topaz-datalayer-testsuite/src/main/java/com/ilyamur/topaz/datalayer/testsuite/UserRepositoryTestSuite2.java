package com.ilyamur.topaz.datalayer.testsuite;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import com.ilyamur.topaz.datalayer.core.service.UserMailingService;
import com.ilyamur.topaz.datalayer.testsuite.service.DatabaseReset;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

// todo apply
@Ignore
public class UserRepositoryTestSuite2 {

    @Autowired
    private DatabaseReset databaseReset;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMailingService userMailingService;

    @Before
    public void before() {
        databaseReset.apply();
    }

    @Test
    public void changeEmail() {
        User user = userRepository.findById(0);
        user.sendEmail("text");
    }
}

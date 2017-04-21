package com.ilyamur.topaz.datalayer.core;

import com.ilyamur.topaz.datalayer.core.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class AspectJTest {

    private static final String USER_EMAIL = "user@email.com";
    private static final String EMAIL_TEXT = "Email text.";

    private User target;

    @Before
    public void before() {
        target = new User();
    }

    @Test(expected = IllegalTransactionStateException.class)
    public void setEmail_outsideTransaction() {
        target.setEmail(USER_EMAIL);
    }

    @Test
    @Transactional
    public void setEmail_insideTransaction() {
        target.setEmail(USER_EMAIL);
    }

    @Test(expected = IllegalTransactionStateException.class)
    @Transactional
    public void sendEmail_insideTransaction() {
        target.sendEmail(EMAIL_TEXT);
    }
}

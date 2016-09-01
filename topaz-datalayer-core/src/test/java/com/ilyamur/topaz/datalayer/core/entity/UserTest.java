package com.ilyamur.topaz.datalayer.core.entity;

import static org.mockito.Mockito.verify;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.core.service.UserMailingService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserTest {

    private static final String USER_EMAIL = "user@email.com";
    private static final String EMAIL_TEXT = "Email text.";

    @Mock
    UserMailingService userMailingService;

    // @InjectMocks
    private User target;

    @Before
    public void before() {
        target = new User();
        target.setEmail(USER_EMAIL);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendEmail() {
        target.sendEmail(EMAIL_TEXT);

        // verify(userMailingService).sendEmail(USER_EMAIL, EMAIL_TEXT);
    }
}

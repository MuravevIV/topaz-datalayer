package com.ilyamur.topaz.datalayer.core.entity;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.core.service.UserMailingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {CoreConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserTest {

    private static final String USER_EMAIL = "user@email.com";
    private static final String EMAIL_TEXT = "Email text.";

    @Mock
    private UserMailingService userMailingService;

    @InjectMocks
    private User target;

    @Before
    @Transactional
    public void before() {
        target.setEmail(USER_EMAIL);
    }

    @Test
    @Transactional
    public void sendEmail() {
        target.sendEmail(EMAIL_TEXT);

        verify(userMailingService).sendEmail(USER_EMAIL, EMAIL_TEXT);
    }
}

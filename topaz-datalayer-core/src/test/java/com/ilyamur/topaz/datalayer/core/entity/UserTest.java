package com.ilyamur.topaz.datalayer.core.entity;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.core.configuration.TestCoreTransactionConfiguration;
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

import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfiguration.class, TestCoreTransactionConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserTest {

    @Mock
    private UserMailingService userMailingService;

    @InjectMocks
    private User user = new User();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendEmail() {
        user.setEmail("user@email.com");
        user.sendEmail("email text");

        verify(userMailingService).sendEmail("user@email.com", "email text");
    }
}

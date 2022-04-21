package com.ilyamur.topaz.datalayer.core.entity;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.CoreConfiguration;
import com.ilyamur.topaz.datalayer.core.configuration.TestCoreTransactionConfiguration;
import com.ilyamur.topaz.datalayer.core.service.UserMailingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CoreConfiguration.class, TestCoreTransactionConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserTest {

    @Mock
    private UserMailingService userMailingService;

    @InjectMocks
    private User user;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendEmail() {
        user.setEmail("user@email.com");
        user.sendEmail("email text");

        verify(userMailingService).sendEmail("user@email.com", "email text");
    }
}

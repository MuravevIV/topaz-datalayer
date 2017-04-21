package com.ilyamur.topaz.datalayer.core.entity;

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
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserTest {

    @Mock
    private UserMailingService userMailingService;

    @InjectMocks
    private User target = new User();

    @Before
    @Transactional
    public void before() {
        target.setEmail("user@email.com");
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalTransactionStateException.class)
    public void setEmail_outsideTransaction() {
        target.setEmail("user@email.com");
    }

    @Test
    @Transactional
    public void setEmail_insideTransaction() {
        target.setEmail("user@email.com");
    }

    @Test
    public void sendEmail() {
        target.sendEmail("email text");

        verify(userMailingService).sendEmail("user@email.com", "email text");
    }
}

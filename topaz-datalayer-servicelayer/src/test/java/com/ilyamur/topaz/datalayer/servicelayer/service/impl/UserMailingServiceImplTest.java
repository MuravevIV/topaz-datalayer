package com.ilyamur.topaz.datalayer.servicelayer.service.impl;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicelayerConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserMailingServiceImplTest {

    @Test
    public void test() {
        User user = new User();
        System.out.println(user);
    }
}

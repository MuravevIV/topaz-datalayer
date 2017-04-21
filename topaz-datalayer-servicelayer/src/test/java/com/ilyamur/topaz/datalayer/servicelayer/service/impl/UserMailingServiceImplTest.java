package com.ilyamur.topaz.datalayer.servicelayer.service.impl;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.servicelayer.ServicelayerConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

// @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicelayerConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserMailingServiceImplTest {
}

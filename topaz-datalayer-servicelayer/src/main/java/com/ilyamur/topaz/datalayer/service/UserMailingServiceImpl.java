package com.ilyamur.topaz.datalayer.service;

import com.ilyamur.topaz.datalayer.core.service.UserMailingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserMailingServiceImpl implements UserMailingService {

    private static final Logger LOG = LoggerFactory.getLogger(UserMailingServiceImpl.class);

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void sendEmail(String email, String text) {
        LOG.info("Sending email to '{}', text: '{}'", email, text);
    }
}

package com.ilyamur.topaz.datalayer.core.service.impl;

import com.ilyamur.topaz.datalayer.core.service.UserMailingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserMailingServiceStub implements UserMailingService {

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void sendEmail(String email, String text) {
    }
}

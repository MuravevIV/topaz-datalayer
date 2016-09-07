package com.ilyamur.topaz.datalayer.core.service.impl;

import com.ilyamur.topaz.datalayer.core.service.UserMailingService;
import org.springframework.stereotype.Service;

@Service
public class UserMailingServiceStub implements UserMailingService {

    @Override
    public void sendEmail(String email, String text) {
    }
}

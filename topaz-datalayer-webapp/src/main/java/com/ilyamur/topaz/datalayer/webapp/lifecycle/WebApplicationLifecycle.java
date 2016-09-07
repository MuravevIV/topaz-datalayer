package com.ilyamur.topaz.datalayer.webapp.lifecycle;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.service.DatabaseReset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile(ApplicationProfile.DEVELOP)
public class WebApplicationLifecycle {

    @Autowired
    private DatabaseReset databaseReset;

    @PostConstruct
    public void onWebApplicationStart() {
        databaseReset.apply();
    }
}

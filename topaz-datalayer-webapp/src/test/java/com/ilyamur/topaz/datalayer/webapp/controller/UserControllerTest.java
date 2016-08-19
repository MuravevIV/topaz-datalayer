package com.ilyamur.topaz.datalayer.webapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.ilyamur.topaz.datalayer.webapp.WebappRootConfiguration;
import com.ilyamur.topaz.datalayer.webapp.WebappServletConfiguration;
import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.service.DatabaseReset;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebappRootConfiguration.class, WebappServletConfiguration.class})
@WebAppConfiguration
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DatabaseReset databaseReset;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        databaseReset.apply();
    }

    @Test
    public void users() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"));
    }

    @Test
    @Ignore // todo fix pojo weaving
    public void usersEmailSend() throws Exception {
        mockMvc.perform(get("/users/email/send"))
                .andExpect(status().isOk());
    }
}

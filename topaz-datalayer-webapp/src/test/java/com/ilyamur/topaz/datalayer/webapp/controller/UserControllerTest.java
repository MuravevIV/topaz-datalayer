package com.ilyamur.topaz.datalayer.webapp.controller;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.core.service.DatabaseReset;
import com.ilyamur.topaz.datalayer.webapp.WebappConfiguration;
import org.junit.Before;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebappConfiguration.class})
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
        mockMvc.perform(get(WebUrl.USERS))
                .andExpect(status().isOk())
                .andExpect(view().name(WebUrl.USERS));
    }

    @Test
    public void usersEmailSend() throws Exception {
        String id = "0";
        String login = "John";
        String emailText = "test";

        mockMvc.perform(get(WebUrl.USERS_EMAIL_SEND)
                .param(UserController.Param.ID, id)
                .param(UserController.Param.EMAIL_TEXT, emailText))
                .andExpect(redirectedUrl(WebUrl.USERS_EMAIL_REPORT))
                .andExpect(flash().attribute(UserController.Param.USER_LOGIN, login))
                .andExpect(flash().attribute(UserController.Param.EMAIL_TEXT, emailText));
    }
}

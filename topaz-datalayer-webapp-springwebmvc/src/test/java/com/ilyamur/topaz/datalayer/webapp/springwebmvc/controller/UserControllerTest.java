package com.ilyamur.topaz.datalayer.webapp.springwebmvc.controller;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;
import com.ilyamur.topaz.datalayer.testsuite.configuration.PostgreSQLTestContainerConfiguration;
import com.ilyamur.topaz.datalayer.testsuite.service.DatabaseReset;
import com.ilyamur.topaz.datalayer.webapp.springwebmvc.Constants;
import com.ilyamur.topaz.datalayer.webapp.springwebmvc.WebappConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringJUnitWebConfig(classes = {WebappConfiguration.class, PostgreSQLTestContainerConfiguration.class})
@ActiveProfiles(ApplicationProfile.TESTING)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DatabaseReset databaseReset;

    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        databaseReset.apply();
    }

    @Test
    public void users() throws Exception {
        mockMvc.perform(get(Constants.Path.USERS))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.Template.USERS));
    }

    @Test
    public void usersEmailSend() throws Exception {
        String id = "1";
        String login = "John";
        String emailText = "test";

        mockMvc.perform(get(Constants.Path.USERS_EMAIL_SEND)
                        .param(Constants.Param.ID, id)
                        .param(Constants.Param.EMAIL_TEXT, emailText))
                .andExpect(redirectedUrl(Constants.Path.USERS_EMAIL_REPORT))
                .andExpect(flash().attribute(Constants.Param.USER_LOGIN, login))
                .andExpect(flash().attribute(Constants.Param.EMAIL_TEXT, emailText));
    }
}

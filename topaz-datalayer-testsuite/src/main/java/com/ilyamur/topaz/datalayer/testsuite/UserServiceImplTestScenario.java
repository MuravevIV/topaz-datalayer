package com.ilyamur.topaz.datalayer.testsuite;

import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;

import java.time.LocalDate;
import java.util.Set;

public interface UserServiceImplTestScenario {

    void nestedExceptionScenario() throws LoginExistsException;

    int getUserCount();

    User createUser(String login, String email, LocalDate birthday, Set<Role> roles);

    User createAnyUser();
}

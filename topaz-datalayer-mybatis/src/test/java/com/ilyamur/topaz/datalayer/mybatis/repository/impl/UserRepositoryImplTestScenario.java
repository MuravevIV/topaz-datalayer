package com.ilyamur.topaz.datalayer.mybatis.repository.impl;

import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;

import java.time.LocalDate;
import java.util.Set;

public interface UserRepositoryImplTestScenario {
    int getUserCount();

    User createUser(String login, String email, LocalDate birthday, Set<Role> roles);

    User createAnyUser();
}

package com.ilyamur.topaz.datalayer.repository;

import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;

import java.time.LocalDate;
import java.util.Set;

public interface UserRepositoryTestHelper {

    User createUser(String login, String email, LocalDate birthday, Set<Role> roles);

    User createAnyUser();
}

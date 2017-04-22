package com.ilyamur.topaz.datalayer.mybatis.repository.impl;

import com.google.common.collect.Sets;
import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.mybatis.repository.UserRepository;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Ignore
@Component
public class UserRepositoryImplTestHelperImpl implements UserRepositoryImplTestScenario {

    private static final String ANY_LOGIN = "Dan";
    private static final String ANY_EMAIL = "any@gmail.com";
    private static final LocalDate ANY_BIRTHDAY = LocalDate.of(1990, Month.APRIL, 27);
    private static final HashSet<Role> ANY_ROLES = Sets.newHashSet(Role.REGISTERED_USER, Role.ADMIN);

    @Autowired
    private UserRepository target;

    @Override
    @Transactional
    public int getUserCount() {
        return target.getAll().size();
    }

    @Override
    public User createUser(String login, String email, LocalDate birthday, Set<Role> roles) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setRoles(roles);
        return user;
    }

    @Override
    public User createAnyUser() {
        return createUser(ANY_LOGIN, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
    }
}

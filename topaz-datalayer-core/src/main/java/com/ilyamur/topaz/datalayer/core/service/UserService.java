package com.ilyamur.topaz.datalayer.core.service;

import com.ilyamur.topaz.datalayer.core.entity.User;

import java.util.Collection;

public interface UserService {

    User findById(long id);

    User findByLogin(String login);

    Collection<User> findAll();

    void changeEmail(long id, String newEmail);

    void delete(User user);
}

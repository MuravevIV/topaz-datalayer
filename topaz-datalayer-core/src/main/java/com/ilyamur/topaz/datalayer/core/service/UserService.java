package com.ilyamur.topaz.datalayer.core.service;

import com.ilyamur.topaz.datalayer.core.entity.User;

import java.util.Collection;

public interface UserService {

    User findById(long id);

    User findByLogin(String login);

    Collection<User> findAll();

    void delete(User user);

    /**
     * Do not conform to the CRUD-repository contract - so defaulted to fail.
     */
    default void changeEmail(long id, String newEmail) {
        throw new UnsupportedOperationException();
    }
}

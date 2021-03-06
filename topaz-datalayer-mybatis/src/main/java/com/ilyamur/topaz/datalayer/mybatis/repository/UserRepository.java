package com.ilyamur.topaz.datalayer.mybatis.repository;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;

import java.util.Collection;

public interface UserRepository {

    User save(User user) throws LoginExistsException;

    Collection<User> saveAll(Collection<User> users) throws LoginExistsException;

    void delete(User user);

    User findById(long idUser);

    User findByLogin(String login);

    Collection<User> getAll();
}

package com.ilyamur.topaz.datalayer.core.repository;

import com.ilyamur.topaz.datalayer.core.entity.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    <S extends User> List<S> saveAll(Iterable<S> entities);

    void delete(User user);

    List<User> findAll();

    //

    User findById(long id);

    User findByLogin(String login);
}

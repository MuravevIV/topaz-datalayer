package com.ilyamur.topaz.datalayer.core.repository;

import com.ilyamur.topaz.datalayer.core.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {

    @Transactional(propagation = Propagation.MANDATORY)
    User save(User user);

    @Transactional(propagation = Propagation.MANDATORY)
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Transactional(propagation = Propagation.MANDATORY)
    void delete(User user);

    List<User> findAll();

    //

    User findById(long id);

    User findByLogin(String login);
}

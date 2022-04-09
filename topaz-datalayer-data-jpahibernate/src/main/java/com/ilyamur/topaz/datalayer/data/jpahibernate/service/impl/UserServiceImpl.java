package com.ilyamur.topaz.datalayer.data.jpahibernate.service.impl;

import com.google.common.collect.Lists;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    /**
     * Usually we converse with a repository directly, here it's proxied to support agnostic testing
     */
    @Autowired
    private UserServiceCrudRepository crudRepository;

    @Override
    public User findById(long id) {
        return crudRepository.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return crudRepository.findByLogin(login);
    }

    @Override
    public Collection<User> findAll() {
        return Lists.newArrayList(crudRepository.findAll());
    }

    @Override
    @Transactional
    public void delete(User user) {
        crudRepository.delete(user);
    }

    @Override
    @Transactional
    public void changeEmail(long id, String newEmail) {
        User user = crudRepository.findById(id);
        user.setEmail(newEmail);
    }
}

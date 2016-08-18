package com.ilyamur.topaz.datalayer.jpahibernate.service.impl;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.service.UserService;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = {LoginExistsException.class})
    public User save(User user) throws LoginExistsException {
        try {
            return entityManager.merge(user);
        } catch (PersistenceException e) {
            throw new LoginExistsException(user.getLogin());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = {LoginExistsException.class})
    public Collection<User> saveAll(Collection<User> users) throws LoginExistsException {
        List<User> savedUsers = Lists.newArrayList();
        for (User user : users) {
            savedUsers.add(save(user));
        }
        return savedUsers;
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        return (User) entityManager.createQuery("from User where login = :login")
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<User> getAll() {
        return entityManager.createQuery("from User").getResultList();
    }
}

package com.ilyamur.topaz.datalayer.jpahibernate.service.impl;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        return (User) entityManager.createQuery("from User where login = :login")
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public Collection<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    @Transactional
    public void changeEmail(long id, String newEmail) {
        User user = findById(id);
        user.setEmail(newEmail);
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }
}

package com.ilyamur.topaz.datalayer.hibernate.service.impl;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User findById(long id) {
        return getSession().get(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        return (User) getSession().createQuery(
                "from User where login = :login")
                .setParameter("login", login)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<User> findAll() {
        return getSession().createQuery("from User").list();
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
        getSession().delete(user);
    }
}

package com.ilyamur.topaz.datalayer.hibernate.repository.impl;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;

import com.google.common.collect.Lists;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserRepositoryImpl implements UserRepository {

    private static final String CONSTRAINT_UNIQUE_LOGIN = "U0_USER_LOGIN";

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(rollbackFor = {LoginExistsException.class})
    public User save(User user) throws LoginExistsException {
        return saveUserInternal(getSession(), user);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = {LoginExistsException.class})
    public Collection<User> saveAll(Collection<User> users) throws LoginExistsException {
        Session session = getSession();
        List<User> savedUsers = Lists.newArrayList();
        for (User user : users) {
            savedUsers.add(saveUserInternal(session, user));
        }
        return savedUsers;
    }

    private User saveUserInternal(Session session, User user) throws LoginExistsException {
        try {
            return (User) session.merge(user);
        } catch (ConstraintViolationException e) {
            if (CONSTRAINT_UNIQUE_LOGIN.equals(e.getConstraintName())) {
                throw new LoginExistsException(user.getLogin());
            } else {
                throw e;
            }
        }
    }

    @Override
    public void delete(User user) {
        getSession().delete(user);
    }

    @Override
    public User findById(long id) {
        return (User) getSession().get(User.class, id);
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
    public Collection<User> getAll() {
        return getSession().createQuery("from User").list();
    }
}

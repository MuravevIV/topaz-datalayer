package com.ilyamur.topaz.datalayer.repository;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HibernateUserRepository implements UserRepository {

    private static final String CONSTRAINT_UNIQUE_LOGIN = "U0_USERS_LOGIN";

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User save(User user) {
        // todo - test "to update"
        try {
            getSession().save(user);
        } catch (Exception e) {
            if (isConstraintViolation(e, CONSTRAINT_UNIQUE_LOGIN)) {
                throw new LoginExistsException(user.getLogin(), e);
            } else {
                throw e;
            }
        }
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        Assert.notNull(entities, "Entities must not be null!");
        List<User> result = new ArrayList<>();
        for (User entity : entities) {
            result.add(save(entity));
        }
        return (List<S>) result;
    }

    @Override
    public void delete(User user) {
        getSession()
                .delete(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return getSession()
                .createQuery("from User")
                .list();
    }

    @Override
    public User findById(long id) {
        return getSession()
                .get(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        return (User) getSession()
                .createQuery("from User where login = :login")
                .setParameter("login", login)
                .uniqueResult();
    }

    private boolean isConstraintViolation(Exception e, String constraintName) {
        if (e instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) e).getSQLException().getMessage();
            return message.toUpperCase().contains(constraintName);
        }
        return false;
    }
}

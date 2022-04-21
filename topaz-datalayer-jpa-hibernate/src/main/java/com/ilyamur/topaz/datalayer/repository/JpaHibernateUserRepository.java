package com.ilyamur.topaz.datalayer.repository;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class JpaHibernateUserRepository implements UserRepository {

    private static final String CONSTRAINT_UNIQUE_LOGIN = "U0_USERS_LOGIN";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        try {
            entityManager.persist(user);
        } catch (PersistenceException e) {
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
        entityManager.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return entityManager
                .createQuery("from User")
                .getResultList();
    }

    @Override
    public User findById(long id) {
        return entityManager
                .find(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        return (User) entityManager
                .createQuery("from User where login = :login")
                .setParameter("login", login)
                .getSingleResult();
    }

    private boolean isConstraintViolation(PersistenceException e, String constraintName) {
        if (e.getCause() instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
            return message.toUpperCase().contains(constraintName);
        }
        return false;
    }
}

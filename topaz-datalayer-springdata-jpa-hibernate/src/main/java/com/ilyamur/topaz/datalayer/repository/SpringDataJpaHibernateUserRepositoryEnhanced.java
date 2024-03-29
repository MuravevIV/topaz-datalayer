package com.ilyamur.topaz.datalayer.repository;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class SpringDataJpaHibernateUserRepositoryEnhanced implements UserRepository {

    private static final String CONSTRAINT_UNIQUE_LOGIN = "U0_USERS_LOGIN";

    @Autowired
    private SpringDataJpaHibernateUserRepository repository;

    @Override
    public User save(User user) {
        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (isConstraintViolation(e, CONSTRAINT_UNIQUE_LOGIN)) {
                throw new LoginExistsException(user.getLogin(), e);
            } else {
                throw e;
            }
        }
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
        repository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    //

    public User findById(long id) {
        return repository.findById(id);
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }

    private boolean isConstraintViolation(DataIntegrityViolationException e, String constraintName) {
        if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
            return StringUtils.equalsIgnoreCase(cve.getConstraintName(), constraintName);
        }
        return false;
    }
}

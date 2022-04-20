package com.ilyamur.topaz.datalayer.repository;

import com.google.common.collect.Lists;
import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import com.ilyamur.topaz.datalayer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class MybatisUserRepository implements UserRepository {

    private static final String CONSTRAINT_UNIQUE_LOGIN = "U0_USER_LOGIN";

    @Autowired
    private UserMapper mapper;

    @Override
    public User save(User user) {
        if (user != null) {
            try {
                updateOrInsert(user);
            } catch (Exception e) {
                if (isConstraintViolation(e, CONSTRAINT_UNIQUE_LOGIN)) {
                    throw new LoginExistsException(user.getLogin(), e);
                } else {
                    throw e;
                }
            }
        }
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        Assert.notNull(entities, "Entities must not be null!");
        ArrayList<User> result = Lists.newArrayList();
        for (User entity : entities) {
            result.add(save(entity));
        }
        return (List<S>) result;
    }

    @Override
    public void delete(User user) {
        mapper.delete(user);
    }

    @Override
    public User findById(long id) {
        return mapper.selectById(id);
    }

    @Override
    public User findByLogin(String login) {
        return mapper.selectByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(mapper.selectAll());
    }

    private void updateOrInsert(User user) {
        if (user.getId() != null) {
            mapper.update(user);
            updateRoles(user);
        } else {
            mapper.insert(user);
            insertRoles(user);
        }
    }

    private void updateRoles(User user) {
        mapper.deleteRoles(user);
        insertRoles(user);
    }

    private void insertRoles(User user) {
        for (Role role : user.getRoles()) {
            mapper.insertRole(user, role);
        }
    }

    private boolean isConstraintViolation(Exception e, String constraintName) {
        return e.getMessage().contains(String.format(" %s ", constraintName));
    }
}

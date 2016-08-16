package com.ilyamur.topaz.datalayer.spring.mybatis.service.impl;

import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.service.UserService;
import com.ilyamur.topaz.datalayer.spring.mybatis.mapper.UserMapper;

import com.google.common.collect.Lists;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private static final String CONSTRAINT_UNIQUE_LOGIN = "U0_USER_LOGIN";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private UserMapper mapper;

    @PostConstruct
    public void postConstruct() {
        mapper = sqlSessionTemplate.getMapper(UserMapper.class);
    }

    @Override
    @Transactional(rollbackFor = {LoginExistsException.class})
    public User save(User user) throws LoginExistsException {
        if (user != null) {
            try {
                updateOrInsert(user);
            } catch (DuplicateKeyException e) {
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
    @Transactional(rollbackFor = {LoginExistsException.class})
    public Collection<User> saveAll(Collection<User> users) throws LoginExistsException {
        ArrayList<User> savedUsers = Lists.newArrayList();
        for (User user : users) {
            savedUsers.add(save(user));
        }
        return savedUsers;
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
    public Collection<User> getAll() {
        return mapper.selectAll();
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

    private boolean isConstraintViolation(DuplicateKeyException e, String constraintName) {
        return e.getMessage().contains(String.format(" %s ", constraintName));
    }
}

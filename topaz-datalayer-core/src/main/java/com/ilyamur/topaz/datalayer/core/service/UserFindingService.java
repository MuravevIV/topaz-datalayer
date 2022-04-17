package com.ilyamur.topaz.datalayer.core.service;

import com.ilyamur.topaz.datalayer.core.entity.User;

import java.util.Collection;

public interface UserFindingService {

    Collection<User> findAll();

    User findById(long id);
}

package com.ilyamur.topaz.datalayer.data.jpahibernate.service.impl;

import com.ilyamur.topaz.datalayer.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServiceCrudRepository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByLogin(String login);
}

package com.ilyamur.topaz.datalayer.repository;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaHibernateUserRepository extends UserRepository, JpaRepository<User, Long> {

    @Override
    User save(User user);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Override
    void delete(User user);

    @Override
    List<User> findAll();

    //

    User findById(long id);

    User findByLogin(String login);
}

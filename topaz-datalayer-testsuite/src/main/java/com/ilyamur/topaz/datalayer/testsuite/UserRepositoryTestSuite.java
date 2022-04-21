package com.ilyamur.topaz.datalayer.testsuite;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import com.ilyamur.topaz.datalayer.testsuite.service.DatabaseReset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTestSuite {

    private static final int EXISTING_ID_USER = 1;
    private static final String EXISTING_LOGIN = "John";
    private static final String ANY_EMAIL = "any@gmail.com";
    private static final LocalDate ANY_BIRTHDAY = LocalDate.of(1990, Month.APRIL, 27);
    private static final Set<Role> ANY_ROLES = Sets.newHashSet(Role.REGISTERED_USER, Role.ADMIN);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseReset databaseReset;

    @Autowired
    private UserRepositoryTestHelper helper;

    @BeforeEach
    public void beforeEach() {
        databaseReset.apply();
    }

    @Test
    @Transactional
    public void anyAction_insideActiveTransaction_isOk() {
        userRepository.findAll();
    }

    @Test
    @Transactional
    public void saveThenFindByIdUser() throws LoginExistsException {
        User user = helper.createAnyUser();
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findById(savedUser.getId());

        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @Transactional
    public void saveThenFindByLogin() throws LoginExistsException {
        User user = helper.createAnyUser();
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findByLogin(user.getLogin());

        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @Transactional
    public void findByIdUserThenSave() throws LoginExistsException {
        User foundUser = userRepository.findById(EXISTING_ID_USER);
        User savedUser = userRepository.save(foundUser);

        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @Transactional
    public void findByLoginThenSave() throws LoginExistsException {
        User foundUser = userRepository.findByLogin(EXISTING_LOGIN);
        User savedUser = userRepository.save(foundUser);

        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @Transactional
    public void updateBirthday() throws LoginExistsException {
        LocalDate newBirthday = LocalDate.of(1985, Month.DECEMBER, 5);
        User updatedUser = userRepository.findByLogin(EXISTING_LOGIN);
        updatedUser.setBirthday(newBirthday);
        userRepository.save(updatedUser);

        User foundUser = userRepository.findByLogin(EXISTING_LOGIN);

        assertThat(foundUser.getBirthday()).isEqualTo(newBirthday);
    }

    @Test
    public void saveUserThenReadUser() {
        User createdUser = helper.createUser("Andy", ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        userRepository.save(createdUser);

        User savedUser = userRepository.findByLogin("Andy");

        assertThat(savedUser).isEqualTo(createdUser);
    }

    @Test
    public void saveTwoUsersWithSameLogin_withinTransaction() {
        String sameLogin = "BigBy";
        User userAbby = helper.createUser(sameLogin, "abby@email.com", ANY_BIRTHDAY, ANY_ROLES);
        User userBrian = helper.createUser(sameLogin, "brian@email.com", ANY_BIRTHDAY, ANY_ROLES);

        LoginExistsException exc = null;
        try {
            saveTwoUsersWithSameLogin_withinTransaction(userAbby, userBrian);
        } catch (LoginExistsException e) {
            exc = e;
        }
        assertThat(exc).describedAs("LoginExistsException expected").isNotNull();
        assertThat(String.format(LoginExistsException.MESSAGE, sameLogin)).isEqualTo(exc.getMessage());

        List<User> users = userRepository.findAll();
        assertThat(users.contains(userAbby)).describedAs("User 'Abby' SHOULD NOT be persisted in the database").isFalse();
        assertThat(users.contains(userBrian)).describedAs("User 'Brian' SHOULD NOT be persisted in the database").isFalse();
    }

    @Transactional
    void saveTwoUsersWithSameLogin_withinTransaction(User userAbby, User userBrian) {
        userRepository.save(userAbby);
        userRepository.save(userBrian);
    }

    @Test
    public void saveTwoUsersWithSameLoginSimultaneously() throws LoginExistsException {
        User userUno = helper.createUser("Uno", ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        userRepository.save(userUno);

        String sameLogin = "BigBy";
        User userAbby = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        User userBrian = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);

        LoginExistsException exc = null;
        try {
            userRepository.saveAll(Lists.newArrayList(userAbby, userBrian));
        } catch (LoginExistsException e) {
            exc = e;
        }
        assertThat(exc).describedAs("LoginExistsException expected").isNotNull();
        assertThat(String.format(LoginExistsException.MESSAGE, sameLogin)).isEqualTo(exc.getMessage());

        List<User> users = userRepository.findAll();
        assertThat(users.contains(userUno)).describedAs("User 'Uno' SHOULD be persisted in the database").isTrue();
        assertThat(users.contains(userAbby)).describedAs("User 'Abby' SHOULD NOT be persisted in the database").isFalse();
        assertThat(users.contains(userBrian)).describedAs("User 'Brian' SHOULD NOT be persisted in the database").isFalse();
    }
}

package com.ilyamur.topaz.datalayer.testsuite;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import com.ilyamur.topaz.datalayer.testsuite.service.DatabaseReset;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@Ignore
public class UserRepositoryTestSuite {

    private static final int EXISTING_ID_USER = 0;
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

    @Before
    public void before() {
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

        assertEquals(savedUser, foundUser);
    }

    @Test
    @Transactional
    public void saveThenFindByLogin() throws LoginExistsException {
        User user = helper.createAnyUser();
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findByLogin(user.getLogin());

        assertEquals(savedUser, foundUser);
    }

    @Test
    @Transactional
    public void findByIdUserThenSave() throws LoginExistsException {
        User foundUser = userRepository.findById(EXISTING_ID_USER);
        User savedUser = userRepository.save(foundUser);

        assertEquals(foundUser, savedUser);
    }

    @Test
    @Transactional
    public void findByLoginThenSave() throws LoginExistsException {
        User foundUser = userRepository.findByLogin(EXISTING_LOGIN);
        User savedUser = userRepository.save(foundUser);

        assertEquals(foundUser, savedUser);
    }

    @Test
    @Transactional
    public void updateBirthday() throws LoginExistsException {
        LocalDate newBirthday = LocalDate.of(1985, Month.DECEMBER, 5);
        User updatedUser = userRepository.findByLogin(EXISTING_LOGIN);
        updatedUser.setBirthday(newBirthday);
        userRepository.save(updatedUser);

        User foundUser = userRepository.findByLogin(EXISTING_LOGIN);

        assertEquals(newBirthday, foundUser.getBirthday());
    }

    // todo - fix
    @Ignore
    @Test
    @Transactional
    public void saveTwoUsersWithSameLoginSequentially_savesOnlyFirstUserAndThrowsLoginExistsException() throws LoginExistsException {
        String sameLogin = "BigBy";
        User userAbby = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        User userBrian = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);

        User savedUserAbby = userRepository.save(userAbby);
        LoginExistsException exc = null;
        try {
            userRepository.save(userBrian);
        } catch (LoginExistsException e) {
            exc = e;
        }

        assertNotNull("LoginExistsException expected", exc);
        assertEquals(String.format(LoginExistsException.MESSAGE, sameLogin), exc.getMessage());
        List<User> users = userRepository.findAll();
        assertTrue("User 'Abby' SHOULD be persisted in the database", users.contains(savedUserAbby));
        assertFalse("User 'Brian' SHOULD NOT be persisted in the database", users.contains(userBrian));
    }

    // todo - fix
    @Ignore
    @Test
    @Transactional
    public void saveTwoUsersWithSameLoginSimultaneously_whenInsideTransaction() throws LoginExistsException {

        User userUno = helper.createUser("Uno", ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        userRepository.save(userUno);

        String sameLogin = "BigBy";
        User userAbby = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        User userBrian = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        try {
            userRepository.saveAll(Lists.newArrayList(userAbby, userBrian));
        } catch (LoginExistsException e) {
            List<User> users = userRepository.findAll();
            assertTrue("User 'Uno' SHOULD be persisted in database", users.contains(userUno));
            assertTrue("User 'Abby' SHOULD be persisted within the current transaction context (will be rolled back)", users.contains(userAbby));
            assertFalse("User 'Brian' SHOULD NOT be persisted within the current transaction context ", users.contains(userBrian));
            return;
        }

        fail("LoginExistsException expected");
    }

    @Test
    public void saveTwoUsersWithSameLoginSimultaneously_whenOutsideTransaction() throws LoginExistsException {

        User userUno = helper.createUser("Uno", ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        userRepository.save(userUno);

        String sameLogin = "BigBy";
        User userAbby = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        User userBrian = helper.createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        try {
            userRepository.saveAll(Lists.newArrayList(userAbby, userBrian));
        } catch (LoginExistsException e) {
            List<User> users = userRepository.findAll();
            assertTrue("User 'Uno' SHOULD be persisted in database", users.contains(userUno));
            assertFalse("User 'Abby' SHOULD NOT be persisted in the database", users.contains(userAbby));
            assertFalse("User 'Brian' SHOULD NOT be persisted in the database", users.contains(userBrian));
            return;
        }

        fail("LoginExistsException expected");
    }
}

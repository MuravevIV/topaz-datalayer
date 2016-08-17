package com.ilyamur.topaz.datalayer.testsuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.exception.LoginExistsException;
import com.ilyamur.topaz.datalayer.core.service.DatabaseReset;
import com.ilyamur.topaz.datalayer.core.service.UserService;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Ignore
public class UserServiceImplTestSuite {

    private static final int EXISTING_ID_USER = 0;
    private static final String EXISTING_LOGIN = "John";
    private static final String ANY_LOGIN = "Dan";
    private static final String ANY_EMAIL = "any@gmail.com";
    private static final LocalDate ANY_BIRTHDAY = LocalDate.of(1990, Month.APRIL, 27);
    private static final HashSet<Role> ANY_ROLES = Sets.newHashSet(Role.REGISTERED_USER, Role.ADMIN);

    @Autowired
    private UserService target;

    @Autowired
    private DatabaseReset databaseReset;

    @Before
    public void before() {
        databaseReset.apply();
    }

    @Test(expected = Exception.class)
    public void anyAction_outsideActiveTransaction_throwsException() {
        target.findById(0);
    }

    @Test
    @Transactional
    public void saveThenFindByIdUser() throws LoginExistsException {
        User user = createAnyUser();
        User savedUser = target.save(user);
        User foundUser = target.findById(savedUser.getId());

        assertEquals(savedUser, foundUser);
    }

    @Test
    @Transactional
    public void saveThenFindByLogin() throws LoginExistsException {
        User user = createAnyUser();
        User savedUser = target.save(user);
        User foundUser = target.findByLogin(user.getLogin());

        assertEquals(savedUser, foundUser);
    }

    @Test
    @Transactional
    public void findByIdUserThenSave() throws LoginExistsException {
        User foundUser = target.findById(EXISTING_ID_USER);
        User savedUser = target.save(foundUser);

        assertEquals(foundUser, savedUser);
    }

    @Test
    @Transactional
    public void findByLoginThenSave() throws LoginExistsException {
        User foundUser = target.findByLogin(EXISTING_LOGIN);
        User savedUser = target.save(foundUser);

        assertEquals(foundUser, savedUser);
    }

    @Test
    @Transactional
    public void updateBirthday() throws LoginExistsException {
        LocalDate newBirthday = LocalDate.of(1985, Month.DECEMBER, 5);
        User updatedUser = target.findByLogin(EXISTING_LOGIN);
        updatedUser.setBirthday(newBirthday);
        target.save(updatedUser);

        User foundUser = target.findByLogin(EXISTING_LOGIN);

        assertEquals(newBirthday, foundUser.getBirthday());
    }

    @Test
    @Transactional
    public void saveTwoUsersWithSameLoginSequentially_savesOnlyFirstUserAndThrowsLoginExistsException()
            throws LoginExistsException {

        String sameLogin = "BigBy";
        User userAbby = createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        User userBrian = createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);

        User savedUserAbby = target.save(userAbby);
        User savedUserBrian = null;
        LoginExistsException exc = null;
        try {
            savedUserBrian = target.save(userBrian);
        } catch (LoginExistsException e) {
            exc = e;
        }

        assertNotNull("LoginExistsException expected", exc);
        assertEquals(String.format(LoginExistsException.MESSAGE, sameLogin), exc.getMessage());
        Collection<User> users = target.getAll();
        assertTrue("User SHOULD be persisted in database", users.contains(savedUserAbby));
        assertFalse("User SHOULD NOT be persisted in database", users.contains(savedUserBrian));
    }

    @Test
    @Transactional
    public void saveTwoUsersWithSameLoginSimultaneously_doNotSaveAnyUserAndThrowsLoginExistsException()
            throws LoginExistsException {

        String sameLogin = "BigBy";
        User userAbby = createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
        User userBrian = createUser(sameLogin, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);

        LoginExistsException exc = null;
        try {
            target.saveAll(Lists.newArrayList(userAbby, userBrian));
        } catch (LoginExistsException e) {
            exc = e;
        }

        assertNotNull("LoginExistsException expected", exc);
        assertEquals(String.format(LoginExistsException.MESSAGE, sameLogin), exc.getMessage());
        Collection<User> users = target.getAll();
        assertFalse("User SHOULD NOT be persisted in database", users.contains(userAbby));
        assertFalse("User SHOULD NOT be persisted in database", users.contains(userBrian));
    }

    private User createUser(String login, String email, LocalDate birthday, Set<Role> roles) {
        User user = new User();
        user.setLogin(login);
        user.setBirthday(birthday);
        user.setRoles(roles);
        return user;
    }

    private User createAnyUser() {
        return createUser(ANY_LOGIN, ANY_EMAIL, ANY_BIRTHDAY, ANY_ROLES);
    }
}

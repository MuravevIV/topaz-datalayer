package com.ilyamur.topaz.datalayer.service;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import com.ilyamur.topaz.datalayer.core.service.UserFindingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class UserFindingServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserFindingService userFindingService = new UserFindingServiceImpl();

    @Test
    public void testFindAll() {
        User a = new User();
        User b = new User();
        doReturn(List.of(a, b)).when(userRepository).findAll();

        Collection<User> users = userFindingService.findAll();

        assertEquals(List.of(a, b), users);
    }
}

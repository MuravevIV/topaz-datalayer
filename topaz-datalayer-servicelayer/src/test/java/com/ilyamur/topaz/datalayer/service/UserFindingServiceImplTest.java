package com.ilyamur.topaz.datalayer.service;

import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserFindingServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserFindingServiceImpl userFindingService;

    @Test
    public void testFindAll() {
        User a = new User();
        User b = new User();

        doReturn(List.of(a, b)).when(userRepository).findAll();

        Collection<User> users = userFindingService.findAll();

        assertThat(users).contains(a, b);
    }
}

package ru.productstar.outcoming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.productstar.outcoming.model.User;
import ru.productstar.outcoming.repository.UserRepository;
import ru.productstar.outcoming.service.HzClient;
import ru.productstar.outcoming.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MockHzClientTest {

    private UserService userService;

    @Mock
    private HzClient hzClient;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, hzClient);
    }

    @Test
    void h2Test() {
        User user = new User("John Doe", 30);
        when(userRepository.save(user)).thenReturn(user);
        when(hzClient.getDataFromService("John Doe")).thenReturn("In-memory Data for John Doe");

        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals("In-memory Data for John Doe", hzClient.getDataFromService(user.getName()));
    }
}

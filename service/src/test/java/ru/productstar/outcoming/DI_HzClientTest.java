package ru.productstar.outcoming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.productstar.outcoming.model.User;
import ru.productstar.outcoming.service.HzClient;
import ru.productstar.outcoming.service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DI_HzClientTest {

    @Autowired
    private UserService userService;

    @Autowired
    private HzClient hzClient;

    @Test
    public void testSaveUserWithDI() {
        User user = new User("John Doe", 30);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertNotNull(hzClient.getDataFromService(user.getName()));
    }
}

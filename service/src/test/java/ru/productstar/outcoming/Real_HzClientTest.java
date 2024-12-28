package ru.productstar.outcoming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.productstar.outcoming.model.User;
import ru.productstar.outcoming.repository.UserRepository;
import ru.productstar.outcoming.service.HzClient;
import ru.productstar.outcoming.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class Real_HzClientTest {

	private UserService userService;
	private HzClient hzClient;
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		userRepository = Mockito.mock(UserRepository.class);
		hzClient = new RealHzClient();
		userService = new UserService(userRepository, hzClient);
	}

	@Test
	void h2Test() {
		User user = new User("John Doe", 30);
		when(userRepository.save(user)).thenReturn(user);
		User savedUser = userService.saveUser(user);
		assertNotNull(savedUser);
		assertEquals("Real Data for John Doe", hzClient.getDataFromService(user.getName()));
	}

	private static class RealHzClient implements HzClient {
		@Override
		public String getDataFromService(String parameter) {
			return "Real Data for " + parameter;
		}
	}
}

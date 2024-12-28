package ru.productstar.outcoming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.productstar.outcoming.model.User;
import ru.productstar.outcoming.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HzClient hzClient;

    @Autowired
    public UserService(UserRepository userRepository, HzClient hzClient) {
        this.userRepository = userRepository;
        this.hzClient = hzClient;
    }

    public User saveUser(User user) {
        String externalData = hzClient.getDataFromService(user.getName());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
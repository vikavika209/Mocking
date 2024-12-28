package ru.productstar.outcoming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.productstar.outcoming.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
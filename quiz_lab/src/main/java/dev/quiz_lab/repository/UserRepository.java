package dev.quiz_lab.repository;

import dev.quiz_lab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByUserName(String userName);
}
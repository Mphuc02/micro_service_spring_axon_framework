package dev.auth_service.repository;

import dev.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
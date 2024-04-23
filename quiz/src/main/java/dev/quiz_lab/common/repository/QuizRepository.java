package dev.quiz_lab.common.repository;

import dev.quiz_lab.common.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
}
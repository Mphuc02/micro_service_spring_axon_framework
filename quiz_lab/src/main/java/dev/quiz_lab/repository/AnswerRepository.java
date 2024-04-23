package dev.quiz_lab.repository;

import dev.quiz_lab.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Choice, UUID> {
}

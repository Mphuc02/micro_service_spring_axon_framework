package dev.quiz_lab.common.repository;

import dev.quiz_lab.common.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Choice, UUID> {
}

package dev.quiz_lab.command.event;

import dev.quiz_lab.common.dto.QuizDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class QuizCreatedEvent {
    private QuizDTO quiz;
    private MultipartFile file;
}
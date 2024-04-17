package dev.quiz_lab.command.command;

import dev.quiz_lab.common.dto.QuizDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class CreateQuizCommand {
    @TargetAggregateIdentifier
    private QuizDTO quiz;
    private byte[] data;
}
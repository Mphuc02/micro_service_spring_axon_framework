package dev.quiz_lab.command.command;

import dev.common_service.command.BaseCommand;
import dev.common_service.model.UserCommon;
import dev.quiz_lab.common.dto.QuizDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
public class CreateQuizCommand extends BaseCommand {
    @TargetAggregateIdentifier
    private final QuizDTO quiz;
    private final byte[] data;

    public CreateQuizCommand(QuizDTO quiz, byte[] data, UserCommon owner){
        this.quiz = quiz;
        this.data = data;
        this.setOwner(owner);
    }
}
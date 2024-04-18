package dev.quiz_lab.command.event;

import dev.common_service.event.BaseEvent;
import dev.common_service.model.UserCommon;
import dev.quiz_lab.common.dto.QuizDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@Getter
public class QuizCreatedEvent extends BaseEvent {
    private QuizDTO quiz;
    private byte[] data;
    private long iat;

    public QuizCreatedEvent(QuizDTO quiz, byte[] data, UserCommon owner){
        this.quiz = quiz;
        this.data = data;
        this.setOwner(owner);
        this.iat = new Date().getTime();
    }
}
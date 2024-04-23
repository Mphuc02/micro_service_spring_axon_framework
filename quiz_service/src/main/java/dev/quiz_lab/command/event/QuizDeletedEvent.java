package dev.quiz_lab.command.event;

import dev.common_service.event.BaseEvent;
import dev.common_service.model.UserCommon;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class QuizDeletedEvent extends BaseEvent {
    private final UUID id;
    private final UserCommon owner;

    public QuizDeletedEvent(UUID id, UserCommon owner){
        this.id = id;
        this.owner = owner;
        this.setIat(new Date().getTime());
    }
}
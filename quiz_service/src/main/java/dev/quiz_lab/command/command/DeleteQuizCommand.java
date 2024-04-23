package dev.quiz_lab.command.command;

import dev.common_service.command.BaseCommand;
import dev.common_service.model.UserCommon;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.util.UUID;

@Getter
public class DeleteQuizCommand extends BaseCommand {
    @TargetAggregateIdentifier
    private final UUID id;
    private final UUID quizId;

    public DeleteQuizCommand(UUID quizId, UserCommon owner){
        this.id = UUID.randomUUID();
        this.quizId = quizId;
        this.setOwner(owner);
    }
}
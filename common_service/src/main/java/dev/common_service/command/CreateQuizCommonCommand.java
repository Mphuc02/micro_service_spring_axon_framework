package dev.common_service.command;

import dev.common_service.model.UserCommon;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class CreateQuizCommonCommand extends BaseCommand {
    @TargetAggregateIdentifier
    private final String quiz;
    private final byte[] data;

    public CreateQuizCommonCommand(String quiz, byte[] data, UserCommon owner){
        this.quiz = quiz;
        this.data = data;
        this.setOwner(owner);
    }
}
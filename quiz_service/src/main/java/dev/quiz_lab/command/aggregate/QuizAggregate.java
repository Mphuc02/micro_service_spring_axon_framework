package dev.quiz_lab.command.aggregate;


import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.common_service.util.CommandExcuteEceptionUtil;
import dev.quiz_lab.command.command.CreateQuizCommand;
import dev.quiz_lab.command.command.DeleteQuizCommand;
import dev.quiz_lab.command.event.QuizCreatedEvent;
import dev.quiz_lab.command.event.QuizDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import java.util.UUID;

@Aggregate

@RequiredArgsConstructor
public class QuizAggregate {
    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public QuizAggregate(CreateQuizCommand command){
        if(command.getData() == null || command.getData().length == 0)
            CommandExcuteEceptionUtil.createException(new BadRequestException(ErrorMessages.EXCEL_NOT_VALID));

        QuizCreatedEvent event = new QuizCreatedEvent(command.getQuiz(), command.getData(), command.getOwner());
        apply(event);
    }

    @CommandHandler
    public QuizAggregate(DeleteQuizCommand command){
        QuizDeletedEvent event = new QuizDeletedEvent(command.getQuizId(), command.getOwner());
        apply(event);
    }

    @EventSourcingHandler
    public void on(QuizCreatedEvent event){
        id = UUID.randomUUID();
    }

    @EventSourcingHandler
    public void on(QuizDeletedEvent event){
        id = UUID.randomUUID();
    }
}
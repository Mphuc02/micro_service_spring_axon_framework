package dev.quiz_lab.command.aggregate;

import dev.quiz_lab.command.command.CreateQuizCommand;
import dev.quiz_lab.command.event.QuizCreatedEvent;
import dev.quiz_lab.common.dto.QuizDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@RequiredArgsConstructor
public class QuizAggregate {
    private QuizDTO quiz;

    @CommandHandler
    public QuizAggregate(CreateQuizCommand command){
        QuizCreatedEvent event = new QuizCreatedEvent(command.getQuiz());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(QuizCreatedEvent event){
        quiz = event.getQuiz();
    }
}
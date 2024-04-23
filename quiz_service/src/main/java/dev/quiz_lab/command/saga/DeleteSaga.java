package dev.quiz_lab.command.saga;

import dev.common_service.event.BaseEvent;
import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.common_service.exception.NotFoundException;
import dev.quiz_lab.command.event.QuizDeletedEvent;
import dev.quiz_lab.common.entity.Quiz;
import dev.quiz_lab.common.repository.QuizRepository;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

@Saga
public class DeleteSaga {
    @Autowired
    private QuizRepository quizRepository;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(QuizDeletedEvent event){
        SagaLifecycle.associateWith("id", event.getId().toString());
        if(!checkEventExcuted(event))
            return;

        Quiz findToDelete = quizRepository.findById(event.getId()).orElseThrow(() -> new NotFoundException(ErrorMessages.QUIZ_NOT_EXIST));
        if(!findToDelete.getAuthor().equals(event.getOwner().getId()))
            throw new BadRequestException(ErrorMessages.NOT_QUIZ_OWNER);
        quizRepository.delete(findToDelete);
    }

    private boolean checkEventExcuted(BaseEvent event){
        long timeNow = new Date().getTime();
        return timeNow - event.getIat() < 60 * 1000;
    }
}
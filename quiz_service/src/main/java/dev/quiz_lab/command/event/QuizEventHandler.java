package dev.quiz_lab.command.event;

import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.quiz_lab.common.repository.QuizRepository;
import dev.quiz_lab.common.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuizEventHandler {
    private final QuizRepository quizRepository;
    private final ExcelUtil excelUtil;
    private final EventBus eventBus;

    @EventHandler
    public void handler(QuizCreatedEvent event){
        throw new BadRequestException(ErrorMessages.AUTHENTICATE_FAIL);
//        Quiz entity = event.getQuiz().mapToEntity();
//        entity.setQuestions(excelUtil.getFromFile(event.getData(), entity));
//        entity.setAuthor(event.getOwner().getId());
//        entity = this.quizRepository.save(entity);
//        log.info("Save new quiz succesfully");
    }

//    @EventHandler
//    public void handler(QuizDeletedEvent event){
//        if(!checkEventExcuted(event))
//            return;
//
//        Quiz findToDelete = quizRepository.findById(event.getId()).orElseThrow(() -> new NotFoundException(ErrorMessages.QUIZ_NOT_EXIST));
//        if(!findToDelete.getAuthor().equals(event.getOwner().getId()))
//            throw new BadRequestException(ErrorMessages.NOT_QUIZ_OWNER);
//
//        quizRepository.delete(findToDelete);
//    }
}
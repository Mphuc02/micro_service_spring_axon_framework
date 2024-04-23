package dev.quiz_lab.command.event;

import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.common_service.exception.NotFoundException;
import dev.quiz_lab.common.entity.Quiz;
import dev.quiz_lab.common.repository.QuizRepository;
import dev.quiz_lab.common.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuizEventHandler {
    private final QuizRepository quizRepository;
    private final ExcelUtil excelUtil;

    @EventHandler
    public void handler(QuizCreatedEvent event){
        Quiz entity = event.getQuiz().mapToEntity();
        entity.setQuestions(excelUtil.getFromFile(event.getData(), entity));
        entity.setAuthor(event.getOwner().getId());
        this.quizRepository.save(entity);
        log.info("Save new quiz succesfully");
    }

    @EventHandler
    public void handler(QuizDeletedEvent event){
        Quiz findToDelete = quizRepository.findById(event.getId()).orElseThrow(() -> new NotFoundException(ErrorMessages.QUIZ_NOT_EXIST));
        if(!findToDelete.getAuthor().equals(event.getOwner().getId()))
            throw new BadRequestException(ErrorMessages.NOT_QUIZ_OWNER);

        quizRepository.delete(findToDelete);
    }
}
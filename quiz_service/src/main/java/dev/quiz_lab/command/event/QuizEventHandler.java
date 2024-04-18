package dev.quiz_lab.command.event;

import dev.quiz_lab.common.entity.Quiz;
import dev.quiz_lab.common.repository.QuizRepository;
import dev.quiz_lab.common.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuizEventHandler {
    private final QuizRepository quizRepository;
    private final ExcelUtil excelUtil;

    @EventHandler
    public UUID handler(QuizCreatedEvent event){
        long timeNow = new Date().getTime();
        if(timeNow - event.getIat() >= 60 * 1000)
            return null;

        Quiz entity = event.getQuiz().mapToEntity();
        entity.setQuestions(excelUtil.getFromFile(event.getData(), entity));
        entity.setAuthor(event.getOwner().getId());
        entity = this.quizRepository.save(entity);
        log.info("Save new quiz succesfully");
        return entity.getId();
    }
}
package dev.quiz_lab.command.event;

import dev.common_service.model.UserCommon;
import dev.quiz_lab.common.entity.Quiz;
import dev.quiz_lab.common.repository.QuizRepository;
import dev.quiz_lab.common.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuizEventHandler {
    private final QuizRepository quizRepository;
    private final ExcelUtil excelUtil;

    @EventHandler
    public String handler(QuizCreatedEvent event){
        Quiz entity = event.getQuiz().mapToEntity();
        entity.setQuestions(excelUtil.getFromFile(event.getFile(), entity));

        UserCommon user = (UserCommon) SecurityContextHolder.getContext().getAuthentication();
        entity.setAuthor(user.getId());

        entity = this.quizRepository.save(entity);
        log.info("Save new quiz succesfully");
        return entity.getId().toString();
    }
}
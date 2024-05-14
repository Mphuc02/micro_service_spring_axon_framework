package dev.quiz_lab.query.projections;

import dev.common_service.exception.ErrorMessages;
import dev.common_service.exception.NotFoundException;
import dev.common_service.service.RedisService;
import dev.quiz_lab.common.dto.QuizDTO;
import dev.quiz_lab.common.entity.Quiz;
import dev.quiz_lab.common.repository.QuizRepository;
import dev.quiz_lab.query.queries.GetDetailQuizQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuizProjection {
    private final QuizRepository quizRepository;
    private final RedisService redisService;

    @QueryHandler
    public QuizDTO findById(GetDetailQuizQuery query){
        Quiz entity = (Quiz) redisService.get(query.getId().toString());
        if(entity == null){
            entity = quizRepository.findById(query.getId())
                    .orElseThrow(() -> new NotFoundException(ErrorMessages.QUIZ_NOT_EXIST));
            redisService.save(query.getId().toString(), entity);
        }

        return new QuizDTO(entity);
    }


}
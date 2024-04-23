package dev.quiz_lab.service;

import dev.quiz_lab.constant.Const;
import dev.quiz_lab.dto.QuizDTO;
import dev.quiz_lab.entity.Quiz;
import dev.quiz_lab.exception.ErrorMessages;
import dev.quiz_lab.exception.NotFoundException;
import dev.quiz_lab.repository.QuizRepository;
import dev.quiz_lab.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {
    private final QuizRepository quizRepository;
    private final ExcelUtil excelUtil;

    public List<QuizDTO> getAll(){
        return this.quizRepository.findAll()
                                    .stream()
                                    .map(QuizDTO::new)
                                    .toList();
    }

    public String save(MultipartFile file, QuizDTO quiz){
        Quiz entity = quiz.mapToEntity();
        entity.setQuestions(excelUtil.getFromFile(file, entity));
        entity = this.quizRepository.save(entity);
        log.info("Save new quiz succesfully");
        return Const.QUIZ_API.PLAY_URL(entity.getId());
    }

    public void delete(UUID id){
        if(!quizRepository.existsById(id)){
            throw new NotFoundException(ErrorMessages.QUIZ_NOT_EXIST);
        }
        quizRepository.deleteById(id);
    }
}
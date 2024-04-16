package dev.quiz_lab.common.service;

import dev.common_service.exception.ErrorMessages;
import dev.common_service.exception.NotFoundException;
import dev.quiz_lab.common.dto.QuizDTO;
import dev.quiz_lab.common.repository.QuizRepository;
import dev.quiz_lab.common.util.ExcelUtil;
import dev.quiz_lab.common.entity.Quiz;
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
        //Todo: Xác định chủ sở hữu của quiz

        Quiz entity = quiz.mapToEntity();
        entity.setQuestions(excelUtil.getFromFile(file, entity));
        entity = this.quizRepository.save(entity);
        log.info("Save new quiz succesfully");
        return entity.getId().toString();
    }

    public QuizDTO findById(UUID id){
        Quiz entity = quizRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.QUIZ_NOT_EXIST));
        return new QuizDTO(entity);
    }

    public void delete(UUID id){
        if(!quizRepository.existsById(id)){
            throw new NotFoundException(ErrorMessages.QUIZ_NOT_EXIST);
        }
        quizRepository.deleteById(id);
    }
}
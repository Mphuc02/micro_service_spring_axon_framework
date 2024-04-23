package dev.quiz_lab.rest;

import dev.quiz_lab.constant.Const.*;
import dev.quiz_lab.dto.QuizDTO;
import dev.quiz_lab.exception.ObjectPropertiesException;
import dev.quiz_lab.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(QUIZ_API.URL)
@RequiredArgsConstructor
@Slf4j
public class QuizRest {
    private final QuizService quizService;

    @GetMapping()
    public ResponseEntity<List<QuizDTO>> getAll(){
        return ResponseEntity.ok(quizService.getAll());
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestPart MultipartFile file,
                                       @Valid @RequestPart QuizDTO quiz,
                                       BindingResult result){
        if(result.hasErrors()){
            throw new ObjectPropertiesException(result.getAllErrors());
        }
        return ResponseEntity.ok(quizService.save(file, quiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        quizService.delete(id);
        return ResponseEntity.ok("ok");
    }
}
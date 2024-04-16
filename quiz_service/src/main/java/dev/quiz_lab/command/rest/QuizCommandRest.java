package dev.quiz_lab.command.rest;

import dev.common_service.exception.ObjectPropertiesException;
import dev.quiz_lab.command.command.CreateQuizCommand;
import dev.quiz_lab.common.dto.QuizDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuizCommandRest {
    private final CommandGateway commandGateway;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> save(@RequestPart MultipartFile file,
                                       @Valid @RequestPart QuizDTO quiz,
                                       BindingResult result){
        if(result.hasErrors()){
            throw new ObjectPropertiesException(result.getAllErrors());
        }

        quiz.setId(UUID.randomUUID());
        CreateQuizCommand command = new CreateQuizCommand(quiz, file);
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("");
    }
}
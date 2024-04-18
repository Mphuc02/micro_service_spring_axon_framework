package dev.quiz_lab.command.rest;

import dev.common_service.exception.ObjectPropertiesException;
import dev.common_service.model.UserCommon;
import dev.quiz_lab.command.command.CreateQuizCommand;
import dev.quiz_lab.common.dto.QuizDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
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
                                       BindingResult result) throws IOException {

        if(result.hasErrors()){
            throw new ObjectPropertiesException(result.getAllErrors());
        }

        UserCommon owner = (UserCommon) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quiz.setId(UUID.randomUUID());
        CreateQuizCommand command = new CreateQuizCommand(quiz, file.getBytes(), owner);
        commandGateway.sendAndWait(command);

        //Todo: Lỗi trả về không đúng id
        return ResponseEntity.ok(quiz.getId());
    }
}
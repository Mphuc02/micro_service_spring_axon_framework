package dev.createquiz.rest;

import com.google.gson.Gson;
import dev.common_service.command.CreateQuizCommonCommand;
import dev.common_service.exception.ObjectPropertiesException;
import dev.common_service.model.UserCommon;
import dev.createquiz.dto.QuizDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/create-quiz")
@Slf4j
public class QuizCommandRest {
    private final CommandGateway commandGateway;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object>save(@RequestPart MultipartFile file,
                                @Valid @RequestPart QuizDTO quiz,
                                BindingResult result) throws IOException{

        if(result.hasErrors()){
            throw ObjectPropertiesException.build(result.getAllErrors());
        }

        UserCommon owner = (UserCommon) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quiz.setId(UUID.randomUUID());
        CreateQuizCommonCommand command = new CreateQuizCommonCommand(new Gson().toJson(quiz), file.getBytes(), owner);
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok(quiz.getId());
    }
}
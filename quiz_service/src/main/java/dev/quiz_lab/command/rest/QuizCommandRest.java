package dev.quiz_lab.command.rest;

import dev.common_service.exception.ObjectPropertiesException;
import dev.common_service.model.UserCommon;
import dev.quiz_lab.command.command.CreateQuizCommand;
import dev.quiz_lab.command.command.DeleteQuizCommand;
import dev.quiz_lab.common.dto.QuizDTO;
import dev.quiz_lab.query.queries.GetDetailQuizQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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
@RequestMapping("/api/v1/quiz")
@Slf4j
public class QuizCommandRest {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?>save(@RequestPart MultipartFile file,
                                                          @Valid @RequestPart QuizDTO quiz,
                                                          BindingResult result) throws IOException{

        if(result.hasErrors()){
            throw new ObjectPropertiesException(result.getAllErrors());
        }

        UserCommon owner = (UserCommon) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quiz.setId(UUID.randomUUID());
        CreateQuizCommand command = new CreateQuizCommand(quiz, file.getBytes(), owner);
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok(quiz.getId());
    }

    @GetMapping("/play/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> play(@PathVariable UUID id){
        GetDetailQuizQuery query = new GetDetailQuizQuery(id);
        QuizDTO quiz = queryGateway.query(query, ResponseTypes.instanceOf(QuizDTO.class))
                                                .join();
        return ResponseEntity.ok(quiz);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        UserCommon user = (UserCommon) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DeleteQuizCommand command = new DeleteQuizCommand(id, user);
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("");
    }
}
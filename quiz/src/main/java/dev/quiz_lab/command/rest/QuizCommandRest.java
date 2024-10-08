package dev.quiz_lab.command.rest;

import dev.common_service.model.UserCommon;
import dev.quiz_lab.command.command.DeleteQuizCommand;
import dev.quiz_lab.common.dto.QuizDTO;
import dev.quiz_lab.query.queries.GetDetailQuizQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
@Slf4j
public class QuizCommandRest {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

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
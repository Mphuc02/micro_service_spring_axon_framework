package dev.common_service.handler;

import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.CommandSuccessException;
import dev.common_service.exception.NotFoundException;
import dev.common_service.exception.ObjectPropertiesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionRestHandler {
    @ExceptionHandler(CommandSuccessException.class)
    public ResponseEntity<Object> handler(CommandSuccessException ex){
        log.info("Excuted successfully event");
        return ResponseEntity.ok(ex.getData());
    }

    @ExceptionHandler(ObjectPropertiesException.class)
    public ResponseEntity<Object> handler(ObjectPropertiesException ex){
        log.error("Object's property not valid: ", ex);
        Map<String, String> result = new HashMap<>();
        ex.getErrors().forEach(error -> result.put(error.getObjectName(), error.getDefaultMessage()));
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handler(BadRequestException ex){
        log.error("Bad request exception: ", ex);
        return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handler(NotFoundException ex){
        log.error("Not found exception by: " + ex.getErrorMessage(), ex);
        return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handler(AccessDeniedException ex){
        log.error("Forbidden exception", ex);
        return new ResponseEntity<>("You must to log in to use this api", HttpStatus.FORBIDDEN);
    }
}
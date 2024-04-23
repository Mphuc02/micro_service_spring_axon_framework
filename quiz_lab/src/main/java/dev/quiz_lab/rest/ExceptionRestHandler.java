package dev.quiz_lab.rest;

import dev.quiz_lab.exception.BadRequestException;
import dev.quiz_lab.exception.NotFoundException;
import dev.quiz_lab.exception.ObjectPropertiesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionRestHandler {
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
}
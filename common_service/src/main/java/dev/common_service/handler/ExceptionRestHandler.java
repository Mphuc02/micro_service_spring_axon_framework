package dev.common_service.handler;

import dev.common_service.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class ExceptionRestHandler {
    @ExceptionHandler(ObjectPropertiesException.class)
    public ResponseEntity<Object> handler(ObjectPropertiesException ex){
        log.error("Object's property not valid: ", ex);
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(CommandExecutionException.class)
    public ResponseEntity<Object> handler(CommandExecutionException ex){
        if(ex.getDetails().isPresent()){
            if(ex.getDetails().get() instanceof ErrorMessage){
                ErrorMessages error = (ErrorMessages) ex.getDetails().get();
                return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(ex.getDetails().get(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Exception when excute event", HttpStatus.BAD_REQUEST);
    }
}
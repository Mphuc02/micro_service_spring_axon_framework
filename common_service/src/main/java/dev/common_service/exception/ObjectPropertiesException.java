package dev.common_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.ObjectError;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ObjectPropertiesException extends RuntimeException{
    private final List<ObjectError> errors;
}
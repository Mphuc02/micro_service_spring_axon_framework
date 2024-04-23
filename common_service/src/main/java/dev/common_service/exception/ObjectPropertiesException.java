package dev.common_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.ObjectError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class ObjectPropertiesException extends RuntimeException{
    private final Map<String, Object> errors;

    public static ObjectPropertiesException build(List<ObjectError> errorFields){
        ObjectPropertiesException exception = new ObjectPropertiesException(new HashMap<>());
        errorFields.forEach(field -> {
            exception.getErrors().put(field.getObjectName(), field.getDefaultMessage());
        });
        return exception;
    }
}
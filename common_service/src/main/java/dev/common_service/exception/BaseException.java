package dev.common_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class BaseException extends RuntimeException{
    private final ErrorMessage errorMessage;
}
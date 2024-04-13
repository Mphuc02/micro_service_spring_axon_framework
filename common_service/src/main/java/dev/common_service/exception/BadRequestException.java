package dev.common_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BadRequestException extends RuntimeException{
    private final ErrorMessage errorMessage;
}
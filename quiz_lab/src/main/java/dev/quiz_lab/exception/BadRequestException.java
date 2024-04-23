package dev.quiz_lab.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BadRequestException extends RuntimeException{
    private final ErrorMessage errorMessage;
}
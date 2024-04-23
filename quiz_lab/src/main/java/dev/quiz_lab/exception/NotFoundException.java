package dev.quiz_lab.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NotFoundException extends RuntimeException{
    private final ErrorMessage errorMessage;
}
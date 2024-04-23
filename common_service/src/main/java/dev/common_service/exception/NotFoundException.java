package dev.common_service.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends BaseException{
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
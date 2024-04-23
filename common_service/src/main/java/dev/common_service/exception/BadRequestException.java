package dev.common_service.exception;

import lombok.Getter;
@Getter
public class BadRequestException extends BaseException{
    public BadRequestException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
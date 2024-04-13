package dev.common_service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessages implements ErrorMessage{
    EXCEL_NOT_VALID(400, "File input nor in format"),
    INDEX_OF_ANSWER_NOT_VALID(400, "Index of answer is not valid"),
    NUMBER_OF_CHOICES_NOT_VALID(400, "Number of choices is not integer"),
    NUMBER_OF_CHOICES_TOO_MUCH(400, "Number of choices must be smaller 7"),
    CONTENT_CHOICE_NOT_VALID(400, "Content of choice must be not null"),
    QUESTION_CONTENT_NOT_BLANK(400, "Question content must not be blank"),
    QUIZ_NOT_EXIST(404, "Quiz with given id not exist"),
    AUTHENTICATE_FAIL(404, "Username or password is incorrect");

    private final int code;
    private final String message;
    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
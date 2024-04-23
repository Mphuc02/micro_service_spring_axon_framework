package dev.quiz_lab.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GetDetailQuizQuery {
    private UUID id;
}
package dev.quiz_lab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.quiz_lab.entity.Quiz;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuizDTO {
    private UUID id;

    @NotBlank(message = "Name must be not null")
    private String name;
    private List<QuestionDTO> questions;

    public QuizDTO(Quiz entity){
        if(entity != null){
            this.id = entity.getId();
            name = entity.getName();
            this.questions = entity.getQuestions().stream().map(QuestionDTO::new).collect(Collectors.toList());
        }
    }

    public Quiz mapToEntity(){
        return Quiz.builder()
                .id(this.id)
                .name(this.name)
                .questions(ObjectUtils.isEmpty(questions) ? new ArrayList<>() : questions.stream()
                                                                                    .map(QuestionDTO::mapToEntity)
                                                                                    .collect(Collectors.toList())
                )
                .build();
    }
}
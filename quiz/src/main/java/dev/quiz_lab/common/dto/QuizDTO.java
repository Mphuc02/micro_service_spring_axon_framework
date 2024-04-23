package dev.quiz_lab.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import dev.common_service.model.UserCommon;
import dev.quiz_lab.common.entity.Quiz;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    @NotEmpty(message = "Must have at least 1 participant")
    private Set<String> participants;

    private UserCommon author;

    public QuizDTO(Quiz entity){
        if(entity != null){
            this.id = entity.getId();
            name = entity.getName();
            this.questions = entity.getQuestions().stream().map(QuestionDTO::new).collect(Collectors.toList());
            this.participants = new Gson().fromJson(entity.getParticipants(), Set.class);
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
                .participants(new Gson().toJson(this.participants))
                .build();
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                ", participants=" + participants +
                ", author=" + author +
                '}';
    }
}
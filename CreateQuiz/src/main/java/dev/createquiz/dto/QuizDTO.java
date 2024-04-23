package dev.createquiz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.common_service.model.UserCommon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
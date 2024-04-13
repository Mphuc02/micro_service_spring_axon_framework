package dev.quiz_lab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.quiz_lab.entity.Question;
import lombok.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuestionDTO {
    private UUID id;
    private List<ChoiceDTO> choices;
    private String content;
    private UUID trueChoice;

    public QuestionDTO(Question entity){
        if(entity != null){
            this.id = entity.getId();
            this.choices = entity.getChoices().stream().map(ChoiceDTO::new).collect(Collectors.toList());
            this.content = entity.getContent();
        }
    }

    public Question mapToEntity(){
        return Question.builder()
                .id(id)
                .content(content)
                .trueChoice(trueChoice)
                .choices(choices.stream().map(ChoiceDTO::mapToEntity).collect(Collectors.toList()))
                .build();
    }
}
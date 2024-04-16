package dev.quiz_lab.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.quiz_lab.common.entity.Choice;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChoiceDTO {
    private UUID id;
    private String content;

    public ChoiceDTO(Choice entity){
        if(entity != null){
            this.id = entity.getId();
            this.content = entity.getContent();
        }
    }

    public Choice mapToEntity(){
        return Choice.builder()
                .id(this.id)
                .content(this.content)
                .build();
    }
}
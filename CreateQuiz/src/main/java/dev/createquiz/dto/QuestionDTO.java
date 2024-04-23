package dev.createquiz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;
import java.util.UUID;

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
}
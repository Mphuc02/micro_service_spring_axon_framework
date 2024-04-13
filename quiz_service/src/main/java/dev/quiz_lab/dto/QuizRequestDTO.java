package dev.quiz_lab.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuizRequestDTO {
    private MultipartFile file;
    private String name;
}
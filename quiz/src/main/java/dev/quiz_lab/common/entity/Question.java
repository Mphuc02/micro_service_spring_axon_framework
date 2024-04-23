package dev.quiz_lab.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "QUESTION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Question {
    @Id
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Choice> choices;

    private String content;

    private UUID trueChoice;
}
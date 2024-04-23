package dev.quiz_lab.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import java.util.UUID;

@Entity
@Table(name = "ANSWER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Choice {
    @Id
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    private String content;

    @ManyToOne()
    @JoinColumn(name = "question_id")
    private Question question;
}
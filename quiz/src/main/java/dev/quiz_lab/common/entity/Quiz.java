package dev.quiz_lab.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "QUIZ")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Quiz {
    @Id
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<Question> questions;

    @Column(columnDefinition = "JSON")
    private String participants;

    @JoinColumn(name = "author_id")
    @JdbcType(VarcharJdbcType.class)
    private UUID author;
}
package dev.quiz_lab.entity;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<Question> questions;

//    @OneToOne()
//    @JoinColumn(name = "user_id")
//    private User owner;
}
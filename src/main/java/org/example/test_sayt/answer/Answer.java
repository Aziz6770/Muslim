package org.example.test_sayt.answer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test_sayt.question.Question;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String must;
    @OneToMany(mappedBy = "answer",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Question>questions;

}

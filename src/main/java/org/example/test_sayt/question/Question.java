package org.example.test_sayt.question;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test_sayt.answer.Answer;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor@Data
@Entity
public class Question {
    @Id
    private UUID id;
    private String question;
    private boolean trueAnswer;
    @ManyToOne
    private Answer answer;
}

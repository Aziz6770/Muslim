package org.example.test_sayt.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "'user'")
public class User {
    @Id
    private UUID id;
    private int trueAnswer;
    private int falseAnswer;
    private int Answers;
}

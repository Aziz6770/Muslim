package org.example.test_sayt.create;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.test_sayt.answer.Answer;
import org.example.test_sayt.answer.AnswerRepository;
import org.example.test_sayt.question.Question;
import org.example.test_sayt.question.QuestionRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/create")
@RequiredArgsConstructor
public class CreateTestController {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    @GetMapping
    public ModelAndView getAll()
    {
        return new ModelAndView("create_Test");
    }
    @PostMapping
    public ModelAndView create(@RequestParam String answer, @RequestParam String question1, @RequestParam String question2, @RequestParam String trueQuestion) {

        Answer answer1=new Answer(UUID.randomUUID(),answer,List.of());
        Answer save = answerRepository.save(answer1);
        Question question=new Question(UUID.randomUUID(),question1,false,save);
        Question question3=new Question(UUID.randomUUID(),question2,false,save);
        Question question4=new Question(UUID.randomUUID(),trueQuestion,true,save);
        questionRepository.save(question);questionRepository.save(question3);questionRepository.save(question4);
        save.setQuestions(List.of(question,question4,question3));
        return new ModelAndView("succesTest");

    }

}

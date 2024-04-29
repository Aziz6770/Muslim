package org.example.test_sayt.delete;

import lombok.RequiredArgsConstructor;
import org.example.test_sayt.answer.Answer;
import org.example.test_sayt.answer.AnswerRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/delete")
@RequiredArgsConstructor
public class DeleteController {
    private final AnswerRepository answerRepository;
    @GetMapping
    public ModelAndView get(Model model) {
        List<Answer> all = answerRepository.findAll();
        Collections.shuffle(all);
        List<Answer> answerList = answersShuffle(all);
        model.addAttribute("answerList", answerList);
        return new ModelAndView("delete", model.asMap());
    }

    private List<Answer> answersShuffle(List<Answer> all) {
        List<Answer> answers = new ArrayList<>();
        for (Answer answer : all) {
            Collections.shuffle(answer.getQuestions());
            answers.add(answer);
        }
        return answers;
    }
    @PostMapping("/{id}")
    public ModelAndView delete(@PathVariable("id")UUID id,Model model)
    {
        System.out.println("id = " + id);
       answerRepository.deleteById(id);
        List<Answer> all = answerRepository.findAll();
        Collections.shuffle(all);
        List<Answer> answerList = answersShuffle(all);
        model.addAttribute("answerList", answerList);
        return new ModelAndView("delete",model.asMap());
    }
}

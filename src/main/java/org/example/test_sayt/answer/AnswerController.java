package org.example.test_sayt.answer;

import lombok.RequiredArgsConstructor;
import org.example.test_sayt.question.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/Answer")
@RequiredArgsConstructor
public  class AnswerController {
    private final AnswerRepository answerRepository;

    @GetMapping
    public ModelAndView get(Model model) {
        List<Answer> all = answerRepository.findAll();
        Collections.shuffle(all);
        List<Answer> answerList = answersShuffle(all);
        model.addAttribute("answerList", answerList);
        return new ModelAndView("test", model.asMap());
    }

    private List<Answer> answersShuffle(List<Answer> all) {
        List<Answer> answers = new ArrayList<>();
        for (Answer answer : all) {
            Collections.shuffle(answer.getQuestions());
            answers.add(answer);
        }
        return answers;
    }


    @PostMapping
    public ModelAndView create(Model model, @RequestParam Map<String, String> formData) {
        AtomicInteger count = new AtomicInteger();
        List<String>stringList=new ArrayList<>();
        List<Answer> all = answerRepository.findAll();
        for (Answer answer : all) {
            formData.forEach((s, s2) ->
            {
                if (UUID.fromString(s).equals(answer.getId())) {
                    answer.getQuestions().forEach(question ->
                    {
                        if (Objects.equals(question.getQuestion(), s2) && question.isTrueAnswer()) {
                            count.addAndGet(1);
                        }
                        else if(question.isTrueAnswer()){
                            stringList.add("Savol:"+answer.getMust()+" Togri javob:"+question.getQuestion());
                        }
                    });

                }
            });
        }
        model.addAttribute("list",stringList);
        model.addAttribute("count", count);
        return new ModelAndView("End", model.asMap());
    }

}
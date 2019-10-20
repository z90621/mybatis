package com.example.demozjy.controller;


import com.example.demozjy.moder.Question;
import com.example.demozjy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/main")
    public String questions(Model model){
        List<Question> list = questionService.findAll();
        System.out.println(">>>>>>>>>>>>>" + list);
        model.addAttribute("list",list);
        return "main";
    }

    @PostMapping(value = "/mains")
    public  String question(String question){
        Question quertions = new Question();
        quertions.setQuestion(question);
        quertions.setUserId(LoginController.loginId);
        questionService.addquestion(quertions);
        return "redirect:/question/main";
    }
    @GetMapping(value = "/answer/{id}")
    public String answer(@PathVariable("id") int id,Model model){
        Question question = questionService.findById(id);
        model.addAttribute("question",question);
        return "question/answer";
    }

    @PostMapping(value = "/answers/{id}")
    public String answer(@PathVariable("id") int id,Question question){
        Question questions = new Question();
        questions =questionService.findById(id);
        questions.setAnswer(question.getAnswer());
        questionService.addanswer(questions);
        return "redirect:/question/main";
    }

    @GetMapping(value = "/answerShow/{id}")
    public String answerShow(@PathVariable("id") int id,Model model){
        Question question = new Question();
        question =  questionService.findById(id);
        model.addAttribute("answerS",question);
        return "question/answerShow";
    }
}

package com.qzapp.quizapp.service;

import com.qzapp.quizapp.dao.QuestionDao;
import com.qzapp.quizapp.dao.QuizDao;
import com.qzapp.quizapp.model.Question;
import com.qzapp.quizapp.model.QuestionWrapper;
import com.qzapp.quizapp.model.Quiz;
import com.qzapp.quizapp.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findByCategory(category);
        System.out.println("Response of randomQuestionsByCategory=" + questions);
        log.info("Response of randomQuestionsByCategory=" ,  questions);
        Collections.shuffle(questions);
        questions = questions.subList(0, Math.min(numQ, questions.size()));
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        System.out.println(questionsFromDB);
        for(Question question : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
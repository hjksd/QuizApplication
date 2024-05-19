package com.qzapp.quizapp.dao;

import com.qzapp.quizapp.model.Question;
import com.qzapp.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository <Quiz, Integer>{


}

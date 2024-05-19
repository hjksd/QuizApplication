package com.qzapp.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qzapp.quizapp.model.Question;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository <Question, Integer>
{
    List<Question> findByCategory(String category);

//    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", na)
//    List<Question> findRandomQuestionsByCategory(String category, int numQ);

    List<Question> findById(int id);

//    List<Question> updateQuestionsById(int id);

    List<Question> deleteByCategory(String category);
}

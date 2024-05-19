package com.qzapp.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qzapp.quizapp.dao.QuestionDao;
import com.qzapp.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	public List<Question> getAllQuestions()
    {
		return questionDao.findAll();

	}
//		try{
//			List<Question> qD = questionDao.findAll();
//			return new ResponseEntity<>(qD, HttpStatus.OK);}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST);
//		}

//Pappi Bhai Logic
//		List<Question> response = questionDao.findAll();
//		response.stream().forEach(question -> question.setOptionFake(question.getOption3()));
//		return response;
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
	{
		try{
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> addQuestion(Question question)
	{
//		try{
//			return new ResponseEntity<>(questionDao.save(question),HttpStatus.BAD_REQUEST);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		questionDao.save(question);
	 	return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public String deleteQuestionsById(int id) {
		questionDao.deleteById(id);
		return "success";
	}

	public List<Question> getQuestionsById(int id) {
		return questionDao.findById(id);
	}

	public String deleteQuestionsByCategory(String category) {
		questionDao.deleteByCategory(category);
		return "success";
	}

	public String updateQuestionsById(int id, Question question) {
//		questionDao.updateQuestionsById(id, question);
//		Check if question exists by ID if not send failure.
		Boolean isExist= questionDao.existsById(id);
		if(isExist)
		{
			questionDao.save(question);
			return "success";
		}
		else {
			return "failure";
		}
	}

}

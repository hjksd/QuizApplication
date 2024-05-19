package com.qzapp.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qzapp.quizapp.model.Question;
import com.qzapp.quizapp.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
   @GetMapping("allQuestions")
	public List<Question> getAllQuestions()
	{
		return questionService.getAllQuestions();
	}
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
	}

	@GetMapping("id/{id}")
	public List<Question> getQuestionsById(@PathVariable int id){
		   return questionService.getQuestionsById(id);
	}

	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question)
	{
		return questionService.addQuestion(question);
	}

	@DeleteMapping("/deleteById/{id}")
	public String deleteQuestionsById(@PathVariable int id){

		   return questionService.deleteQuestionsById(id);
	}

//	@DeleteMapping("deleteByCategory/{category}")
//	public String deleteQuestionsByCategory(@PathVariable String category){
//		return questionService.deleteQuestionsByCategory(category);
//	}

	@PutMapping("/updateByID/{id}")
	public String updateQuestionsById(@RequestBody Question question,@PathVariable int id){
		return questionService.updateQuestionsById(id, question);
	}
}

/* TODO:
1. Get all questions(GET MAPPING)
2. Get questions by Id
3. Create Questions(POST MAPPING)
4. Update Questions(PATCH MAPPING)
5. Delete Questions(DELETE MAPPING)
6. Get 1 option from user and validate the question. (DB Related activity) - Response-> True/False Congo.String->Json format
7. Change the table name.


1.get question by id- done
2.delete question by category- done

 */
package com.project.askquestion.controller;

/*Author: Aditya Prajapati
 * Modified Date :25-08-2022
 * Description : Created controller class for EndUser
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.askquestion.dto.AskQuestionDTO;
import com.project.askquestion.dto.MessageDTO;
import com.project.askquestion.dto.PostAnswerDTO;
import com.project.askquestion.entity.Answer;

import com.project.askquestion.entity.Question;
import com.project.askquestion.entity.EndUser;
import com.project.askquestion.service.IEndUserService;
import com.project.askquestion.vo.Message;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class EndUserController {

	@Autowired
	private IEndUserService userService;
	
	@GetMapping("/login/{email}/{password}") // working
	public EndUser userLogin(@PathVariable String email, @PathVariable String password) {
		return userService.userLogin(email, password);
	}

	@GetMapping("/logout/{userId}") // working
	public String userLogout(@PathVariable Long userId) {
		return userService.userLogout(userId);
	}

	@PostMapping("/register") // working
	public EndUser userRegister(@RequestBody EndUser user) {
		return userService.userRegister(user);
	}

	@PostMapping("/askQuestion") // working
	public Question askQuestion(@RequestBody AskQuestionDTO askQuestionDTO) {
		return userService.askQuestion(askQuestionDTO);
	}

	@PostMapping("/giveAnswer") // working
	public Answer giveAnswer(@RequestBody PostAnswerDTO postAnswerDTO) {
		return userService.giveAnswer(postAnswerDTO);
	}

	@GetMapping("/searchQuestion/{question}")  // not working  --> resolved
	public List<Question> searchQuestion(@PathVariable String question) {
		return userService.searchQuestion(question);
	}

	@GetMapping("/getAnswers/{questionId}") // not working  --> resolved
	public List<Answer> getAnswers(@PathVariable Long questionId) {
		return userService.getAnswers(questionId);
	}

	@GetMapping("/getQuestions/{topic}") // not working --> resolved
	public List<Question> getQuestions(@PathVariable String topic) {
		return userService.getQuestions(topic);
	}

	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/sendMessage")
	public Message sendMessage(MessageDTO dto) {

		String url = "http://localhost:8282/chat/sendMessage";
		ResponseEntity<MessageDTO> responseEntity = restTemplate.postForEntity(url, dto, MessageDTO.class);
		MessageDTO response = responseEntity.getBody();

		Message mess = new Message();
		mess.setFromUser(response.getFromUser());
		mess.setMessage(response.getMessage());
		return mess;
	}
	
	@GetMapping("/getMessage")
	public List<MessageDTO> getMessage(){
		
		String url = "http://localhost:8282/chat/getMessage";
		ResponseEntity<List> response =	restTemplate.getForEntity(url, List.class);
		
	
			List<MessageDTO> list =	response.getBody();
	
		return list;		
	}
	
	
	@GetMapping("/getAllQuestions")
	public List<Question> getAllQuestions()
	{
		return userService.getAllQuestions();
	}
	
}

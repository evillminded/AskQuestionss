package com.project.askquestion.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.askquestion.dto.AskQuestionDTO;
import com.project.askquestion.dto.PostAnswerDTO;
import com.project.askquestion.entity.Answer;
import com.project.askquestion.entity.EndUser;
import com.project.askquestion.entity.Question;
@SpringBootTest
class EndUserServiceImplTest {
	
	@Autowired
	IEndUserService service;

	@Autowired
	EndUserServiceImpl Impl;

	@Test
	void testUserRegister() {	
		EndUser user = new EndUser("akanshaa","akanshaa123","mnbv@gmail.com","9874563215");
		EndUser userregistered=service.userRegister(user);
		assertNotNull(userregistered);
	}
	
	@Test
	void testUserLogin() {
		EndUser userlogin=service.userLogin("mnbv@gmail.com","akanshaa123");
		assertNotNull(userlogin);
	}

	@Test
	void testUserLogout() {
		String userlogout=service.userLogout(1L);
		assertNotNull(userlogout);
	}
	
	@Test
	void testAskQuestion() {
		AskQuestionDTO dto = new AskQuestionDTO(2L,"what is c++..?","java");
		Question askquestion = service.askQuestion(dto);
		assertNotNull(askquestion);
	}

	@Test
	void testGiveAnswer() {
		PostAnswerDTO dto = new PostAnswerDTO(2L,1L,"c+= is programming language");
		Answer answer = service.giveAnswer(dto);
		assertNotNull(answer);
	}

	@Test
	void testGetQuestions() {
		List<Question> list = service. getQuestions("java");
		assertNotNull(list);
	}

	@Test
	void testSendMail() {
		Boolean val = Impl.sendMail("mnbv@gmail.com","testcase");
		assertNotNull(val);
	}

	@Test
	void testGetAllQuestions() {
		List<Question> list = service. getAllQuestions();
		assertNotNull(list);
	}

	@Test
	void testSearchQuestion() {
		List<Question> list = service. searchQuestion("java");
		assertNotNull(list);
	}

	@Test
	void testGetAnswers() {
		List<Answer> list = service. getAnswers(1L);
		assertNotNull(list);
	}

}
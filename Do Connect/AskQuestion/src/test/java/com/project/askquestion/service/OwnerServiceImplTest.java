package com.project.askquestion.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.askquestion.dto.ResponseDTO;
import com.project.askquestion.entity.Answer;
import com.project.askquestion.entity.EndUser;
import com.project.askquestion.entity.Owner;
import com.project.askquestion.entity.Question;
@SpringBootTest
class OwnerServiceImplTest {
	
	@Autowired
	IOwnerService service;
	
	@Autowired
	OwnerServiceImpl Impl;
	
	@Test
	void testAdminRegister() {
		
		Owner owner = new Owner("vivek","vivek123","amit@gmail.com","987456321");
		Owner adminregistered=service.adminRegister(owner);
		assertNotNull(adminregistered);
	}

	@Test
	void testAdminLogin() {
		String adminlogin=service.adminLogin("amit@gmail.com","vivek123");
		assertNotNull(adminlogin);
	}

	@Test
	void testAdminLogout() {
			String adminlogout=service.adminLogout(1L);
			assertNotNull(adminlogout);
	}

	@Test
	void testGetUnApprovedQuestions() {
		List<Question> list = service.getUnApprovedQuestions();
		assertNotNull(list);
	}

	@Test
	void testGetUnApprovedAnswers() {
		List<Answer> list = service.getUnApprovedAnswers();
		assertNotNull(list);
	}
	
	@Test
	void testApproveQuestion() {
		Question list = service.approveQuestion(1L);
		assertNotNull(list);
	}

	@Test
	void testApproveAnswer() {
		Answer list = service.approveAnswer(2L);
		assertNotNull(list);
	}

	@Test
	void testDeleteQuestion() {
		ResponseDTO deletequestion=service.deleteQuestion(2L);
		assertNotNull(deletequestion);
	}

	@Test
	void testDeleteAnswer() {
		ResponseDTO deleteanswer=service.deleteAnswer(2L);
		assertNotNull(deleteanswer);
	}

	@Test
	void testSendMail() {
		Boolean val = Impl.sendMail("amit@gmail.com","testcase");
		assertNotNull(val);
	}

	@Test
	void testGetUser() {
		EndUser user = service.getUser("pottt@gmail.com");
		assertNotNull(user);
	}

	@Test
	void testGetAllUser() {
		List<EndUser> list = service.getAllUser();
		assertNotNull(list);
	}


	@Test
	void testGetAllOwner() {
		List<Owner> list=service.getAllOwner();
		assertNotNull(list);
	}

	@Test
	void testGetAllQuestions() {
		List<Question> list=service.getAllQuestions();
		assertNotNull(list);
	}

}
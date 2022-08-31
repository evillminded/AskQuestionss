package com.project.askquestion.controller;

/*Author: Venu Gopal Reddy
 * Modified Date :25-08-2022
 * Description : Created controller for owner class
 */

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.askquestion.dto.ResponseDTO;
import com.project.askquestion.entity.Owner;
import com.project.askquestion.entity.Answer;
import com.project.askquestion.entity.Question;
import com.project.askquestion.repository.IOwnerRepo;
import com.project.askquestion.entity.EndUser;
import com.project.askquestion.service.IOwnerService;

@RestController
@RequestMapping("/owner")
@CrossOrigin("http://localhost:4200")
public class OwnerController {

	@Autowired
	private IOwnerService adminService;

	@GetMapping("/login/{email}/{password}")  // working
	public String adminLogin(@PathVariable String email, @PathVariable String password) {
		return adminService.adminLogin(email, password);
	}
	// change the resonse to admin or user

	@GetMapping("/logout/{adminId}") // working
	public String adminLogout(@PathVariable Long adminId) {
		return adminService.adminLogout(adminId);
	}

	@PostMapping("/register") // working
	public Owner adminRegister(@RequestBody Owner admin) {
		return adminService.adminRegister(admin);
	}

	@GetMapping("/getUnApprovedQuestions") // working
	public List<Question> getUnApprovedQuestions() {
		return adminService.getUnApprovedQuestions();
	}

	@GetMapping("/getUnApprovedAnswers") // working
	public List<Answer> getUnApprovedAnswers() {
		return adminService.getUnApprovedAnswers();
	}

	@GetMapping("/approveQuestion/{questionId}") // working
	public Question approveQuestion(@PathVariable Long questionId) {
		return adminService.approveQuestion(questionId);
	}

	@GetMapping("/approveAnswer/{answerId}") // working
	public Answer approveAnswer(@PathVariable Long answerId) {
		return adminService.approveAnswer(answerId);
	}

	@DeleteMapping("/deleteQuestion/{questionId}") // working
	public ResponseDTO deleteQuestion(@PathVariable Long questionId) {
		return adminService.deleteQuestion(questionId);
	}

	@DeleteMapping("/deleteAnswer/{answerId}") // working
	public ResponseDTO deleteAnswer(@PathVariable Long answerId) {
		return adminService.deleteAnswer(answerId);
	}

	@GetMapping("/getUser/{email}") // working
	public EndUser getUser(@PathVariable String email) {
		return adminService.getUser(email);
	}

	@GetMapping("/getAllUsers")  // working
	public List<EndUser> getAllUser() {
		return adminService.getAllUser();
	}

	@DeleteMapping("/deleteUser/{id}") // working
	public String deleteUser(@PathVariable Long id)
	{
		return adminService.deleteUser(id);
	}
	
	@GetMapping("/getAllOwner")
	public List<Owner> getAllOwner()
	{
		return adminService.getAllOwner();
	}
	
	@GetMapping("/getAllQuestions")
	public List<Question> getAllQuestions()
	{
		return adminService.getAllQuestions();
	}
}

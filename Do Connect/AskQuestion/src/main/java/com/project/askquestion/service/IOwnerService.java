package com.project.askquestion.service;
/*Author: Nahush
 * Modified Date :25-08-2022
 * Description : Interface for owner
 */

import java.util.List;

import com.project.askquestion.dto.ResponseDTO;
import com.project.askquestion.entity.Owner;
import com.project.askquestion.entity.Answer;
import com.project.askquestion.entity.Question;
import com.project.askquestion.entity.EndUser;

public interface IOwnerService {

	public String adminLogin(String email, String password);

	public String adminLogout(Long adminId);

	public Owner adminRegister(Owner admin);

	public List<Question> getUnApprovedQuestions();

	public List<Answer> getUnApprovedAnswers();

	public Question approveQuestion(Long questionId);

	public Answer approveAnswer(Long answerId);

	public ResponseDTO deleteQuestion(Long questionId);

	public ResponseDTO deleteAnswer(Long answerId);

	public EndUser getUser(String email);

	public List<EndUser> getAllUser();
	
	public String deleteUser(Long id);
	
	public List<Owner> getAllOwner();
	
	public List<Question> getAllQuestions();

}
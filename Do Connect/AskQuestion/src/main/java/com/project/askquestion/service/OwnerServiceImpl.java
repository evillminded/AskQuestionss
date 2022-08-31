package com.project.askquestion.service;

/*Author: Venu Gopal Reddy
 * Modified Date :25-08-2022
 * Description : implementation of owner service
 */

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.askquestion.dto.ResponseDTO;
import com.project.askquestion.entity.Owner;
import com.project.askquestion.entity.Answer;
import com.project.askquestion.entity.Question;
import com.project.askquestion.entity.EndUser;
import com.project.askquestion.exception.AlreadyThere;
import com.project.askquestion.exception.NotFound;
import com.project.askquestion.repository.IOwnerRepo;
import com.project.askquestion.repository.IAnswerRepo;
import com.project.askquestion.repository.IQuestionRepo;
import com.project.askquestion.repository.IEndUserRepo;
import com.project.askquestion.util.EmailSenderService;

@Service
public class OwnerServiceImpl implements IOwnerService {

	@Autowired
	private IOwnerRepo adminRepo;

	@Autowired
	private IQuestionRepo questionRepo;

	@Autowired
	private IAnswerRepo answerRepo;

	@Autowired
	private IEndUserRepo userRepo;

	@Autowired
	private EmailSenderService emailSenderService;
	
	HashMap<Long, String> map = new HashMap<>();

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : adminLogin
	 * Params:adminRepo
	 * Return type : String
	 */
	@Override
	public String adminLogin(String email, String password) {

		if(map.isEmpty())
		{
			Owner admin = adminRepo.findByEmail(email);
			if (Objects.isNull(admin))
				throw new NotFound();
			
			if (admin.getPassword().equals(password)) {
				admin.setIsActive(true);
				adminRepo.save(admin);
				map.put(admin.getId(), admin.getPassword());
			} else
				throw new NotFound();
			return "Login successfull";			
		}
		else
			return "already logged in";
		
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : adminLogout
	 * Params:adminRepo
	 * Return type : String
	 */
	@Override
	public String adminLogout(Long adminId) {

		if(map.isEmpty())
			return "Please login first!";
		else
		{
			Owner admin = adminRepo.findById(adminId).orElseThrow(() -> new NotFound("Admin not found"));
			admin.setIsActive(false);
			adminRepo.save(admin);
			map.remove(admin.getId());
			return "Logged Out";			
		}
	}
	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : adminRegister
	 * Params:adminRepo
	 * Return type : owner
	 */

	@Override
	public Owner adminRegister(Owner admin) {

		Owner admin1 = adminRepo.findByEmail(admin.getEmail());
		if (Objects.isNull(admin1))
			return adminRepo.save(admin);

		throw new AlreadyThere();
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : getUnApprovedQuestion
	 * Params:questionRepo
	 * Return type : List
	 */
	@Override
	public List<Question> getUnApprovedQuestions() {
		if(map.isEmpty())
			return null;
		else
			return questionRepo.findByIsApproved();
	}

	
	/*Author:Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : getUnApprovedAnswers
	 * Params:answerRepo
	 * Return type : List
	 */
	@Override
	public List<Answer> getUnApprovedAnswers() {
		if(map.isEmpty())
			return null;
		else
			return answerRepo.findByIsApproved();
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : approveQuestion
	 * Params:adminREpo
	 * Return type : Question
	 */
	@Override
	public Question approveQuestion(Long questionId) {
		
			Question question = questionRepo.findById(questionId).orElseThrow(() -> new NotFound("Question not found"));
			
			question.setIsApproved(true);
			question = questionRepo.save(question);
			
			List<Owner> admins = adminRepo.findAll();
			for (Owner admin : admins) {
				sendMail(admin.getEmail(), "Question Added");
			}
			// a mail should go to the list of admins that the question is approved
			
			return question;			
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : approveAnswer
	 * Params:answerRepo
	 * Return type : Answer
	 */
	@Override
	public Answer approveAnswer(Long answerId) {
		
			Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new NotFound("Answer not found"));
			
			answer.setIsApproved(true);
			answer = answerRepo.save(answer);
			
			List<Owner> admins = adminRepo.findAll();
			for (Owner admin : admins) {
				sendMail(admin.getEmail(), "Answer Added");
			}
			
			// a mail should go to the admin that a answer is published
			return answer;
			
	}

	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : deleteQuestion
	 * Params:questionRepo
	 * Return type : ResponseDTO
	 */
	@Override
	public ResponseDTO deleteQuestion(Long questionId) {
			ResponseDTO responseDTO = new ResponseDTO();
			Question question = questionRepo.findById(questionId).orElseThrow(() -> new NotFound("Question not found"));
			
			questionRepo.delete(question);
			responseDTO.setMsg("Question removed");
			return responseDTO;			

	}

	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : deleteAnswer
	 * Params:answerRepo
	 * Return type : ResponseDTO
	 */
	@Override
	public ResponseDTO deleteAnswer(Long answerId) {

			ResponseDTO responseDTO = new ResponseDTO();
			
			Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new NotFound("Answer not found"));
			
			answerRepo.delete(answer);
			responseDTO.setMsg("Answer Removed");
			return responseDTO;			
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : sendMail
	 * Params:emailID
	 * Return type : Boolean
	 */
	public Boolean sendMail(String emailId, String type) {
		try {
			emailSenderService.sendEmail(emailId, type, type);
			return true;
		} catch (Exception e) {
			System.out.println("error in sending mail " + e);
			return false;
		}
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : getUser
	 * Params:userRepo
	 * Return type : EndUser
	 */
	@Override
	public EndUser getUser(String email) {
			return userRepo.findByEmail(email);
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : getallUser
	 * Params:userRepo
	 * Return type : List
	 */
	@Override
	public List<EndUser> getAllUser() {
//		if(map.isEmpty())
//			return null;
//		else
			return userRepo.findAll();
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : deleteUser
	 * Paramsid:useRepo
	 * Return type : String
	 */
	@Override
	public String deleteUser(Long id) {
		if(map.isEmpty())
			return "Please login as a admin first to delete user";
		else
		{
			userRepo.deleteById(id);
			return "User Deleted!";			
		}
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : getAllOwner
	 * Params:adminRepo
	 * Return type : List
	 */
	public List<Owner> getAllOwner()
	{
		return adminRepo.findAll();
	}

	
	/*Author: Venu Gopal Reddy
	 * Modified Date :25-08-2022
	 * Description : getallQuestion
	 * Params:questionRepo
	 * Return type : List
	 */
	@Override
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}
}

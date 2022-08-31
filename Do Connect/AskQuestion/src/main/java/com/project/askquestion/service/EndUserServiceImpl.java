package com.project.askquestion.service;

/*Author: Aditya Prajapati
 * Modified Date :25-08-2022
 * Description : Created Implementation class for EndUser
 */

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.askquestion.dto.AskQuestionDTO;
import com.project.askquestion.dto.PostAnswerDTO;
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
public class EndUserServiceImpl implements IEndUserService {

	@Autowired
	private IEndUserRepo userRepo;

	@Autowired
	private IQuestionRepo questionRepo;

	@Autowired
	private IAnswerRepo answerRepo;
	
	@Autowired
	private IOwnerRepo adminRepo;
	
	@Autowired
	private EmailSenderService emailSenderService;

	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : user login
	 * Params:userRepo
	 * Return type : EndUser
	 */
	
	@Override
	public EndUser userLogin(String email, String password) {

		EndUser user = userRepo.findByEmail(email);
		if (Objects.isNull(user))
			throw new NotFound();

		if (user.getPassword().equals(password)) {
			user.setIsActive(true);
			userRepo.save(user);
		} else
			throw new NotFound();
		return user;
	}
	
	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : user logout
	 * Params:userRepo
	 * Return type : String
	 */

	@Override
	public String userLogout(Long userId) {

		EndUser user = userRepo.findById(userId).orElseThrow(() -> new NotFound("User Not Found" + userId));
		user.setIsActive(false);
		userRepo.save(user);

		return "Logged Out";
	}
	
	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : user register
	 * Params:userRepo
	 * Return type : EndUser
	 */

	@Override
	public EndUser userRegister(EndUser user) {

		EndUser user1 = userRepo.findByEmail(user.getEmail());
		if (Objects.isNull(user1))
			return userRepo.save(user);

		throw new AlreadyThere();
	}
	
	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : ask question
	 * Params:userRepo
	 * Return type : Question
	 */

	@Override
	public Question askQuestion(AskQuestionDTO askQuestionDTO) {
		Question question = new Question();

		EndUser user = userRepo.findById(askQuestionDTO.getUserId()).orElseThrow(() -> new NotFound("User Not Found"));
		question.setQuestion(askQuestionDTO.getQuestion());
		question.setTopic(askQuestionDTO.getTopic());
		question.setUser(user);
		question.setUserName(askQuestionDTO.getUserName());
		questionRepo.save(question);
		
		List<Owner> admins = adminRepo.findAll();
		for (Owner admin : admins) {
			sendMail(admin.getEmail(), "Question Added");
		}
		
		return question;
	}

	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : give answer
	 * Params:userRepo,postAnswerDTO
	 * Return type : EndAnswer
	 */
	
	@Override
	public Answer giveAnswer(PostAnswerDTO postAnswerDTO) {
		Answer answer = new Answer();
		EndUser answerUser = userRepo.findById(postAnswerDTO.getUserId())
				.orElseThrow(() -> new NotFound("User Not Found"));

		Question question = questionRepo.findById(postAnswerDTO.getQuestionId())
				.orElseThrow(() -> new NotFound("Question Not Found"));
		answer.setQuestion(question);
		answer.setAnswer(postAnswerDTO.getAnswer());
		answer.setAnswerUser(answerUser);
		answer.setQuesId(postAnswerDTO.getQuestionId());

		answerRepo.save(answer);
		
		List<Owner> admins = adminRepo.findAll();
		for (Owner admin : admins) {
			sendMail(admin.getEmail(), "Answer Added");
		}
		return answer;
	}
	
	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : search Question
	 * Params:userRepo,postAnswerDTO
	 * Return type : List
	 */

	@Override
	public List<Question> searchQuestion(String question) {
		return questionRepo.findBYQuestion(question);
	}

	
	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : get answers
	 * Params:answerRepo
	 * Return type : List
	 */
	@Override
	public List<Answer> getAnswers(Long questionId) {
		return answerRepo.findByQuestionId(questionId);
	}
	
	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : get Question
	 * Params:questionRepo
	 * Return type : List
	 */

	@Override
	public List<Question> getQuestions(String topic) {
		if (topic.equalsIgnoreCase("All")) {
			return questionRepo.findByIsApprovedTrue();
		}
		return questionRepo.findBYTopicLike(topic);
	}

	
	/*Author: Aditya Prajapati
	 * Modified Date :25-08-2022
	 * Description : sendMail
	 * Params:emailid,
	 * Return type : Boolean
	 */
	@Override
	public Boolean sendMail(String emailId, String type) {
		try {
			emailSenderService.sendEmail(emailId, type, type);
			return true;
		} catch (Exception e) {
			System.out.println("error in sending mail " + e);
			return false;
		}
	}

	/*Author: Aditya Prajapati
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

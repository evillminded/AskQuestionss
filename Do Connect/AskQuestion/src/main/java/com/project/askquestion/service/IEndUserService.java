package com.project.askquestion.service;

/*Author: Shivam Kumar Singh
 * Modified Date :25-08-2022
 * Description : Interface for enduser
 */

import java.util.List;

import com.project.askquestion.dto.AskQuestionDTO;
import com.project.askquestion.dto.PostAnswerDTO;
import com.project.askquestion.entity.Answer;
import com.project.askquestion.entity.Question;
import com.project.askquestion.entity.EndUser;
import com.project.askquestion.vo.Message;

public interface IEndUserService {

	public EndUser userLogin(String email, String password);

	public String userLogout(Long userId);

	public EndUser userRegister(EndUser user);

	public Question askQuestion(AskQuestionDTO askQuestionDTO);

	public Answer giveAnswer(PostAnswerDTO postAnswerDTO);

	public List<Question> searchQuestion(String question);

	public List<Answer> getAnswers(Long questionId);

	public List<Question> getQuestions(String topic);

	public List<Question> getAllQuestions();
	
	public Boolean sendMail(String emailId, String type);


}

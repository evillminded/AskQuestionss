package com.project.askquestion.entity;

/*Author: Shivam Kumar Singh
 * Modified Date :25-08-2022
 * Description : Created Entity class for Owner 
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String answer;
	private Long quesId;
	@OneToOne
	private EndUser answerUser;
	@OneToOne
	private Question question;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public EndUser getAnswerUser() {
		return answerUser;
	}

	public void setAnswerUser(EndUser answerUser) {
		this.answerUser = answerUser;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	private Boolean isApproved = false;

}

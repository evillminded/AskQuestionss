package com.project.askquestion.dto;


/*Author: Nahush
 * Modified Date :25-08-2022
 * Description : Created dto class for askquestiondto
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AskQuestionDTO {

	private Long userId;
	private String userName;
	private String question;
	private String topic;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public AskQuestionDTO(Long userId, String question, String topic) {
		super();
		this.userId = userId;
		this.question = question;
		this.topic = topic;
	}
	
}

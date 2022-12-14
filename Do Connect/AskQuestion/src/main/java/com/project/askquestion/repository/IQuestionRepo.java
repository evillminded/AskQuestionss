package com.project.askquestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.askquestion.entity.Question;

@Repository
public interface IQuestionRepo extends JpaRepository<Question, Long> {

	@Query("from Question where isApproved = false")
	public List<Question> findByIsApproved();

	@Query("from Question where isApproved = true")
	public List<Question> findByIsApprovedTrue();

	@Query("from Question where topic =?1 and isApproved = true")
	public List<Question> findByTopicAndApproved(String topic);
	
	@Query("SELECT q FROM Question q WHERE q.topic LIKE %:topic%")
	public List<Question> findBYTopicLike(@Param("topic") String topic);
	
	@Query("SELECT q FROM Question q WHERE q.question LIKE %:question%")
	public List<Question> findBYQuestion(@Param("question") String question);

}

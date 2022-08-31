package com.project.askquestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.askquestion.entity.Answer;

@Repository
public interface IAnswerRepo extends JpaRepository<Answer, Long> {

	@Query("select a from Answer a where a.quesId=:Id")
	public List<Answer> findByQuestionId(@Param("Id") Long questionId);

	@Query("from Answer where isApproved = false")
	public List<Answer> findByIsApproved();
}

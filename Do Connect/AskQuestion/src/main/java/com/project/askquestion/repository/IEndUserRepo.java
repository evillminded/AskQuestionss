package com.project.askquestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.askquestion.entity.EndUser;

@Repository
public interface IEndUserRepo extends JpaRepository<EndUser, Long> {

	public EndUser findByEmail(String email);
}

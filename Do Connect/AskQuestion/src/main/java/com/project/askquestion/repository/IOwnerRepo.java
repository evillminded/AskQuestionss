package com.project.askquestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.askquestion.entity.Owner;

@Repository
public interface IOwnerRepo extends JpaRepository<Owner, Long> {

	public Owner findByEmail(String email);

}

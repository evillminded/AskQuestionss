	package com.project.askquestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.askquestion.entity.*;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);
}

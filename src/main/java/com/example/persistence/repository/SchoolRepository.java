package com.example.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
	public Optional<School> findBySchoolName(String schoolName);
}

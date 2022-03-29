package com.example.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence.entity.Course;
import com.example.persistence.entity.converter.CourseEnum;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	public Optional<Course> findByCourseName(CourseEnum courseName);

}

package com.example.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}

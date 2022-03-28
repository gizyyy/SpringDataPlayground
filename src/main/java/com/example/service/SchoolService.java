package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.persistence.entity.Student;
import com.example.persistence.repository.StudentRepository;

@Service
public class SchoolService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

}

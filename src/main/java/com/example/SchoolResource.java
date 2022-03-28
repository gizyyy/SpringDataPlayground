package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.PublicView;
import com.example.persistence.entity.Student;
import com.example.service.SchoolService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class SchoolResource {

	@Autowired
	private SchoolService schoolService;

	@GetMapping(value = "/students")
	@JsonView(value = PublicView.class)
	public List<Student> students() {
		return schoolService.getStudents();
	}

}

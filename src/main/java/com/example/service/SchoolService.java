package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.persistence.entity.Address;
import com.example.persistence.entity.Course;
import com.example.persistence.entity.School;
import com.example.persistence.entity.Student;
import com.example.persistence.entity.StudentCreatedEvent;
import com.example.persistence.entity.converter.ObjectName;
import com.example.persistence.repository.AddressRepository;
import com.example.persistence.repository.CourseRepository;
import com.example.persistence.repository.SchoolRepository;
import com.example.persistence.repository.StudentRepository;
import com.example.request.CourseDTO;
import com.example.request.StudentRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
public class SchoolService {

	private StudentRepository studentRepository;
	private AddressRepository addressRepository;
	private CourseRepository courseRepository;
	private SchoolRepository schoolRepository;

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Transactional
	public void addStudent(final StudentRequest s) {
		final Student studentToSave = new Student();
		studentToSave.setStudentName(new ObjectName(s.getStudentName()));
		getOrCreateAddress(s, studentToSave);

		List<Course> list = new ArrayList<Course>();
		s.getCourses().stream().forEach(k -> {
			getOrCreateCourse(list, k);
		});

		studentToSave.setCourses(list);
		studentRepository.save(studentToSave.announceCreation());
	}

	@TransactionalEventListener
	public void handleStudentCreatedEvent(StudentCreatedEvent event) {
		System.out.println("Student Added:" + event.getStu().getStudentName().getName());
	}

	private void getOrCreateCourse(final List<Course> list, final CourseDTO k) {
		Optional<Course> course = courseRepository.findByCourseName(k.getCourseName());
		if (course.isPresent()) {
			list.add(course.get());
		} else {
			Course c = new Course();
			c.setCourseName(k.getCourseName());
			getOrCreateSchool(k, c);
			list.add(c);
		}
	}

	private void getOrCreateSchool(final CourseDTO k, final Course c) {
		Optional<School> school = schoolRepository.findBySchoolName(k.getSchoolName());
		if (school.isPresent()) {
			c.setSchool(school.get());
		} else {
			School sc = new School();
			sc.setSchoolName(ObjectName.create(k.getSchoolName()));
			c.setSchool(sc);
		}
	}

	private void getOrCreateAddress(final StudentRequest s, final Student studentToSave) {
		Optional<Address> city = addressRepository.findByCityName(ObjectName.create(s.getCityName()));

		if (city.isPresent()) {
			studentToSave.setAddress(city.get());
		} else {
			Address a = new Address();
			a.setCityName(new ObjectName(s.getCityName()));
			studentToSave.setAddress(a);
		}
	}

}

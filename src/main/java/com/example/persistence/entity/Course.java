package com.example.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.config.PrivateView;
import com.example.persistence.entity.converter.CourseEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COURSE")
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(value = PrivateView.class )
	private Integer id;

	@Column(name = "course_name")
	@Enumerated(EnumType.STRING)
	private CourseEnum courseName;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "school_id")
	@JsonIgnoreProperties(value = { "courses" })
	private School school;

	@ManyToMany
	@JoinTable(name = "course_students", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnoreProperties(value = { "courses" })
	private List<Student> students;
}

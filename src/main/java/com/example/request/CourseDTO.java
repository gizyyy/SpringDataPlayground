package com.example.request;

import com.example.persistence.entity.converter.CourseEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CourseDTO {

	private CourseEnum courseName;
	private String schoolName;
}

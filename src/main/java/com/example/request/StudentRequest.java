package com.example.request;

import java.util.List;

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
public class StudentRequest {
	
	private String studentName;
	private String cityName;
	private List<CourseDTO> courses;
}

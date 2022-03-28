package com.example.persistence.entity.converter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum CourseEnum{

	MATH("MATH"), GERMAN("GERMAN"), PHYSICS("PHYSICS"), @JsonEnumDefaultValue
	OTHER("OTHER");

	private static final Map<String, CourseEnum> LOOKUP_BY_NAME = Arrays.stream(values())
			.collect(Collectors.toMap(CourseEnum::name, Function.identity()));

	@JsonValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value;

	@JsonCreator
	public static CourseEnum getByName(String name) {
		return LOOKUP_BY_NAME.get(name);
	}

}

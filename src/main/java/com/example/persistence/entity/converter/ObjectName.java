package com.example.persistence.entity.converter;

import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectName {
	private String name;

	private static final long serialVersionUID = 1L;

	@JsonCreator
	public static ObjectName create(final String name) {
		ObjectName result = new ObjectName();
		result.setName(name);
		return result;
	}
}

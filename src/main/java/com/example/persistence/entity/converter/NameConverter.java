package com.example.persistence.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NameConverter implements AttributeConverter<ObjectName, String> {

	public String convertToDatabaseColumn(final ObjectName attribute) {
		return attribute == null ? null : attribute.getName();
	}

	public ObjectName convertToEntityAttribute(final String name) {
		return ObjectName.create(name);
	}
}

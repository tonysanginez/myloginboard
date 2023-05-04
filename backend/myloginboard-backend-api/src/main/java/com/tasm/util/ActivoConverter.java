package com.tasm.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ActivoConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute ? "A" : "I";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return "A".equalsIgnoreCase(dbData);
	}
	
}
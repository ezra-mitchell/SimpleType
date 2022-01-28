package com.afs.ezra.simpletype.provider.themes.model.serialization;

import java.awt.Color;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ColorAttributeConverter implements AttributeConverter<Color, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Color c) {
		return c.getRGB();
	}

	@Override
	public Color convertToEntityAttribute(Integer i) {
		return new Color(i);
	}

}

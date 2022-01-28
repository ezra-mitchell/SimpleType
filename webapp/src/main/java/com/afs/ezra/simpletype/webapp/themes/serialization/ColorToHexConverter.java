package com.afs.ezra.simpletype.webapp.themes.serialization;

import java.awt.Color;
import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class ColorToHexConverter extends JsonSerializer<Color> {

	@Override
	public void serialize(Color value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String hexColor = String.format("#%2s%2s%2s", Integer.toHexString(value.getRed()),
				Integer.toHexString(value.getGreen()), Integer.toHexString(value.getBlue())).replaceAll(" ", "0");
		gen.writeString(hexColor);
	}

}

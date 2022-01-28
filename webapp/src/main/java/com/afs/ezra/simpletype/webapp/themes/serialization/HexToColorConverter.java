package com.afs.ezra.simpletype.webapp.themes.serialization;

import java.awt.Color;
import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@JsonComponent
public class HexToColorConverter extends JsonDeserializer<Color> {

	@Override
	public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String hex = p.getValueAsString().replace("#", "").replace(" ", "");
		if(hex.length() == 3) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 3; i++) {
				sb.append(hex.charAt(i));
				sb.append(hex.charAt(i));
			}
			hex = sb.toString();
		}
		return new Color(Integer.valueOf(hex, 16));
	}
}

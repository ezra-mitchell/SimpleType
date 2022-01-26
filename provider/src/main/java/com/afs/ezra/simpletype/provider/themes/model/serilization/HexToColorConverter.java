package com.afs.ezra.simpletype.provider.themes.model.serilization;

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
		return new Color(Integer.valueOf(p.getValueAsString().replaceAll("#", "").replaceAll(" ", ""), 16));
	}
}

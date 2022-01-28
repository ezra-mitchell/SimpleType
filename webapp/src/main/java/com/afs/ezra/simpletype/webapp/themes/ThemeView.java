package com.afs.ezra.simpletype.webapp.themes;

import java.awt.Color;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThemeView implements Theme {

	public ThemeView(Theme theme) {
		BeanUtils.copyProperties(theme, this);
	}

	public static ThemeView convert(Theme theme) {
		if (theme == null) {
			return null;
		}
		if (theme instanceof ThemeView) {
			return (ThemeView) theme;
		}
		return new ThemeView(theme);
	}

	@NotBlank
	private String name;
	private Long id;
	@NotNull
	@JsonProperty("--c-bg-main")
	private Color backgroundMain;
	@NotNull
	@JsonProperty("--c-bg-secondary")
	private Color backgroundSecondary;
	@NotNull
	@JsonProperty("--c-accent")
	private Color accent;
	@NotNull
	@JsonProperty("--c-accent-light")
	private Color accentLight;
	@NotNull
	@JsonProperty("--c-neutral")
	private Color neutral;
	@NotNull
	@JsonProperty("--c-not-typed")
	private Color notTyped;
	@NotNull
	@JsonProperty("--c-error")
	private Color error;
	@NotNull
	@JsonProperty("--c-correct")
	private Color correct;
}

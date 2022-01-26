package com.afs.ezra.simpletype.provider.themes.model;

import java.awt.Color;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
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
	private Color backgroundMain;
	@NotNull
	private Color backgroundSecondary;
	@NotNull
	private Color accent;
	@NotNull
	private Color accentLight;
	@NotNull
	private Color neutral;
	@NotNull
	private Color notTyped;
	@NotNull
	private Color error;
	@NotNull
	private Color correct;
}

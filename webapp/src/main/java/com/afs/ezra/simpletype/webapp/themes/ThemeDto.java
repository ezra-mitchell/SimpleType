package com.afs.ezra.simpletype.webapp.themes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ThemeDto {

	@NotBlank
	private String name;
	private Long id;
	@NotNull
	private ThemeColorsDto colors;
}

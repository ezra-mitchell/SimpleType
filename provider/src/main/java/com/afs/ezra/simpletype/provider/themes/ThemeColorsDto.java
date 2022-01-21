package com.afs.ezra.simpletype.provider.themes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ThemeColorsDto {

	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-bg-main")
	private String backgroundMain;
	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-bg-secondary")
	private String backgroundSecondary;
	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-accent")
	private String accent;
	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-accent-light")
	private String accentLight;
	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-neutral")
	private String neutral;
	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-not-typed")
	private String notTyped;
	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-error")
	private String error;
	@NotNull
	@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$")
	@JsonProperty("--c-correct")
	private String correct;
}

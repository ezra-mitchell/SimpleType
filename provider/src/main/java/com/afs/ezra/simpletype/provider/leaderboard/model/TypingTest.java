package com.afs.ezra.simpletype.provider.leaderboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TypingTest {
	@JsonProperty("text[]")
	public String text;
	@JsonProperty("errors[]")
	public String errors;
	public String name;
}

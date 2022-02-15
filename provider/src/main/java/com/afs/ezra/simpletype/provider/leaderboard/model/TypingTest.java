package com.afs.ezra.simpletype.provider.leaderboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TypingTest {
	@JsonProperty("text[]")
	public String text;
	@JsonProperty("errors[]")
	public String errors;
	public String name;
}

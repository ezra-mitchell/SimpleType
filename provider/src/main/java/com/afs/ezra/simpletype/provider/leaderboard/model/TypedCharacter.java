package com.afs.ezra.simpletype.provider.leaderboard.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TypedCharacter {

	private Character character;
	private Character typed;
	private Double timeTyped;
}

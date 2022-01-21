package com.afs.ezra.simpletype.provider.leaderboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TypedCharacterDTO {

	private Character character;
	private Character typed;
	private Double timeTyped;
}

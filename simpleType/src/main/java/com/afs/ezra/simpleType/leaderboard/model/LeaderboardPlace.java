package com.afs.ezra.simpleType.leaderboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeaderboardPlace {
	
	private int place;
	private String name;
	private double speed;
	private double accuracy;

}

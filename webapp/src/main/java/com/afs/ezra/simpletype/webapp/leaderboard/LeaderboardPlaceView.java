package com.afs.ezra.simpletype.webapp.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeaderboardPlaceView implements LeaderboardPlace{

	private Long place;
	private String name;
	private Double speed;
	private Double accuracy;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}

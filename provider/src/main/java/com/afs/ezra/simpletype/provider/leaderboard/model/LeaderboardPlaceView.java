package com.afs.ezra.simpletype.provider.leaderboard.model;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LeaderboardPlaceView implements LeaderboardPlace{

	private Long place;
	private String name;
	private Double speed;
	private Double accuracy;
	
	public LeaderboardPlaceView(LeaderboardPlace place) {
		BeanUtils.copyProperties(place, this);
	}
	
	public static LeaderboardPlaceView convert(LeaderboardPlace place) {
		if(place == null) return null;
		
		if(place instanceof LeaderboardPlaceView) return (LeaderboardPlaceView) place;
		
		return new LeaderboardPlaceView(place);
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}

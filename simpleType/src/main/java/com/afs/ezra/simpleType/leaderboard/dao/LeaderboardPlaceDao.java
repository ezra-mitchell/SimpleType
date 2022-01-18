package com.afs.ezra.simpleType.leaderboard.dao;

import java.util.List;

import com.afs.ezra.simpleType.leaderboard.model.LeaderboardPlace;

public interface LeaderboardPlaceDao {

	void saveLeaderboardPlace(LeaderboardPlace leaderboardPlace);
	
	void deleteLeaderboardPlaceById(long id);
	
	List<LeaderboardPlace> getTopLeaderboardPlaces(int count);
	
	List<LeaderboardPlace> getTopLeaderboardPlacesAround(LeaderboardPlace leaderboardPlace);
	
}

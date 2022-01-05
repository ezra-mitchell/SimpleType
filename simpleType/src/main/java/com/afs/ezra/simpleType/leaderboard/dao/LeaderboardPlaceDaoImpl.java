package com.afs.ezra.simpleType.leaderboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.afs.ezra.simpleType.common.AbstractDao;
import com.afs.ezra.simpleType.leaderboard.model.LeaderboardPlace;


@Repository
public class LeaderboardPlaceDaoImpl extends AbstractDao implements LeaderboardPlaceDao {

	@Override
	public void saveLeaderboardPlace(LeaderboardPlace leaderboardPlace) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteLeaderboardPlaceById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<LeaderboardPlace> getTopLeaderboardPlaces(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LeaderboardPlace> getTopLeaderboardPlacesAround(LeaderboardPlace leaderboardPlace) {
		// TODO Auto-generated method stub
		return null;
	}

}

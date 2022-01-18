package com.afs.ezra.simpleType.leaderboard.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.afs.ezra.simpleType.common.AbstractDao;
import com.afs.ezra.simpleType.leaderboard.model.LeaderboardPlace;

@Repository
@Transactional
public class LeaderboardPlaceDaoImpl extends AbstractDao implements LeaderboardPlaceDao {

	@Override
	public void saveLeaderboardPlace(LeaderboardPlace leaderboardPlace) {
		persist(leaderboardPlace);
	}

	@Override
	public void deleteLeaderboardPlaceById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<LeaderboardPlace> getTopLeaderboardPlaces(int count) {
		Query<LeaderboardPlace> query = getSession().createQuery("FROM LeaderboardPlace P ORDER BY P.speed DESC, P.accuracy DESC", LeaderboardPlace.class);
		query.setMaxResults(count);
		
		return query.list();
	}

	@Override
	public List<LeaderboardPlace> getTopLeaderboardPlacesAround(LeaderboardPlace leaderboardPlace) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.afs.ezra.simpletype.provider.leaderboard;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaderboardRepository extends PagingAndSortingRepository<LeaderboardPlace, Long> {
	
	@Query("SELECT p FROM LeaderboardPlace p ORDER BY p.speed, p.accuracy")
	List<LeaderboardPlace> getTopLeaderboard(Pageable pageable);
}

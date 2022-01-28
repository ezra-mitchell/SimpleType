package com.afs.ezra.simpletype.provider.leaderboard.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceImpl;

public interface LeaderboardRepository extends PagingAndSortingRepository<LeaderboardPlaceImpl, Long> {
	
	@Query("SELECT p FROM LeaderboardPlaceImpl p ORDER BY p.speed DESC, p.accuracy DESC")
	List<LeaderboardPlaceImpl> getTopLeaderboard(Pageable pageable);
	
	@Query("select count(*) FROM LeaderboardPlaceImpl l where l.speed > :#{#place.speed}")
	Long getPlacement(@Param("place") LeaderboardPlaceImpl place);
}

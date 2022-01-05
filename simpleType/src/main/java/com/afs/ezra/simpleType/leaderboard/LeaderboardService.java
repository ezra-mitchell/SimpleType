package com.afs.ezra.simpleType.leaderboard;

import java.io.IOException;
import java.util.List;

import com.afs.ezra.simpleType.leaderboard.model.LeaderboardPlace;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LeaderboardService {
	
	List<LeaderboardPlace> postLeaderboardScore(String textJSON, String errorsJSON, String name, int age) throws JsonParseException, JsonMappingException, IOException;

	List<LeaderboardPlace> getTopLeaderboard(int length);
}

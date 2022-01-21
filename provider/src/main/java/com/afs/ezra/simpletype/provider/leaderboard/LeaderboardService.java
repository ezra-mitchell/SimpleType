package com.afs.ezra.simpletype.provider.leaderboard;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LeaderboardService {
	
	List<LeaderboardPlaceDTO> postLeaderboardScore(String textJSON, String errorsJSON, String name, int age) throws JsonParseException, JsonMappingException, IOException;

	List<LeaderboardPlaceDTO> getTopLeaderboard(int length);
}

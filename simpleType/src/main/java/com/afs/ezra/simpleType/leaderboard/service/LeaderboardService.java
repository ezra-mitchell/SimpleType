package com.afs.ezra.simpleType.leaderboard.service;

import java.io.IOException;
import java.util.List;

import com.afs.ezra.simpleType.leaderboard.dto.LeaderboardPlaceDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LeaderboardService {
	
	List<LeaderboardPlaceDTO> postLeaderboardScore(String textJSON, String errorsJSON, String name, int age) throws JsonParseException, JsonMappingException, IOException;

	List<LeaderboardPlaceDTO> getTopLeaderboard(int length);
}

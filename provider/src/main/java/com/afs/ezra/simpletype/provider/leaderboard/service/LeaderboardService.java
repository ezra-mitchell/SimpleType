package com.afs.ezra.simpletype.provider.leaderboard.service;

import java.io.IOException;
import java.util.List;

import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceView;
import com.afs.ezra.simpletype.provider.leaderboard.model.TypingTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LeaderboardService {
	
	LeaderboardPlaceView postLeaderboardScore(TypingTest testData) throws JsonParseException, JsonMappingException, IOException;

	List<LeaderboardPlaceView> getTopLeaderboard(int length);
}

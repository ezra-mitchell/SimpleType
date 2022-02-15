package com.afs.ezra.simpletype.provider.leaderboard.service;

import java.io.IOException;
import java.util.List;

import com.afs.ezra.simpletype.provider.common.HttpException;
import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceView;
import com.afs.ezra.simpletype.provider.leaderboard.model.TypingTest;

public interface LeaderboardService {
	
	LeaderboardPlaceView postLeaderboardScore(TypingTest testData) throws  IOException, HttpException;

	List<LeaderboardPlaceView> getTopLeaderboard(int length) throws HttpException;
}

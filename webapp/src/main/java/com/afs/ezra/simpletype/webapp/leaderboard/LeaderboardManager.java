package com.afs.ezra.simpletype.webapp.leaderboard;

import org.springframework.stereotype.Component;


@Component
public interface LeaderboardManager {

	public LeaderboardPlace postLeaderboardScore(TypingTest testData);

	public LeaderboardPlace[] getTopLeaderboard(int i);

}

package com.afs.ezra.simpletype.webapp.leaderboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class LeaderboardManager {
	
	private final RestTemplate restTemplate;
	
	@Value("${manager.url}")
	private String url;

	public LeaderboardPlace postLeaderboardScore(TypingTest testData) {
		HttpEntity<TypingTest> entity = new HttpEntity<TypingTest>(testData);
		
		return restTemplate.postForObject(url + "/leaderboard", entity, LeaderboardPlaceView.class);
	}

	public LeaderboardPlace[] getTopLeaderboard(int i) {
		return restTemplate.getForObject(url + "/leaderboard/" + i, LeaderboardPlaceView[].class);
	}

}

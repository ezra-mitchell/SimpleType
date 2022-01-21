package com.afs.ezra.simpletype.webapp.leaderboard;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class LeaderboardManager {
	
	private final RestTemplate restTemplate;

	public List<LeaderboardPlaceDTO> postLeaderboardScore(String textJSON, String errorsJSON, String name, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getTopLeaderboard(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.afs.ezra.simpletype.webapp.leaderboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class LeaderboardManager {
	
	private final RestTemplate restTemplate;
	
	@Value("${manager.url}")
	private String url;

	public LeaderboardPlaceDTO[] postLeaderboardScore(String textJSON, String errorsJSON, String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("text[]", textJSON);
		map.add("errors[]", errorsJSON);
		map.add("name", name);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		return restTemplate.postForObject(url + "/post-leaderboard", entity, LeaderboardPlaceDTO[].class);
	}

	public LeaderboardPlaceDTO[] getTopLeaderboard(int i) {
		return restTemplate.getForObject(url + "/leaderboard/" + i, LeaderboardPlaceDTO[].class);
	}

}

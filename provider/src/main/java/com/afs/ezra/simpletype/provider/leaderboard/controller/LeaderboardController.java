package com.afs.ezra.simpletype.provider.leaderboard.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afs.ezra.simpletype.provider.common.HttpException;
import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceView;
import com.afs.ezra.simpletype.provider.leaderboard.model.TypingTest;
import com.afs.ezra.simpletype.provider.leaderboard.service.LeaderboardService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LeaderboardController {

	private final LeaderboardService leaderboardService;

	@PostMapping("/leaderboard")
	public ResponseEntity<LeaderboardPlaceView> postLeaderboardScore(@RequestBody TypingTest testData)
			throws JsonParseException, JsonMappingException, IOException, HttpException {

		LeaderboardPlaceView place = leaderboardService.postLeaderboardScore(testData);

		return ResponseEntity.ok(place);
	}

	@GetMapping("/leaderboard/{size}")
	public ResponseEntity<List<LeaderboardPlaceView>> getTopLeaderboard(
			@PathVariable(name = "size", required = true) Integer size) throws HttpException {
		return ResponseEntity.ok(leaderboardService.getTopLeaderboard(size));
	}

}

package com.afs.ezra.simpletype.provider.leaderboard;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LeaderboardController {

	private final LeaderboardService leaderboardService;

	@PostMapping("/post-leaderboard")
	public ResponseEntity<List<LeaderboardPlaceDTO>> postLeaderboardScore(@RequestParam("text[]") String textJSON,
			@RequestParam("errors[]") String errorsJSON, @RequestParam(value = "name") String name)
			throws JsonParseException, JsonMappingException, IOException {

		List<LeaderboardPlaceDTO> leaderboard = leaderboardService.postLeaderboardScore(textJSON, errorsJSON, name);

		return ResponseEntity.ok(leaderboard);
	}

	@GetMapping("/leaderboard/{size}")
	public ResponseEntity<List<LeaderboardPlaceDTO>> getTopLeaderboard(
			@PathVariable(name = "size", required = true) Integer size) {
		return ResponseEntity.ok(leaderboardService.getTopLeaderboard(size));
	}

}

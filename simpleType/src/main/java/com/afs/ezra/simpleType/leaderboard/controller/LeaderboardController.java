package com.afs.ezra.simpleType.leaderboard.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.afs.ezra.simpleType.leaderboard.dto.LeaderboardPlaceDTO;
import com.afs.ezra.simpleType.leaderboard.service.LeaderboardService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LeaderboardController {

	private final LeaderboardService leaderboardService;

	@PostMapping("/post-leaderboard")
	public ModelAndView postLeaderboardScore(@RequestParam("text[]") String textJSON,
			@RequestParam("errors[]") String errorsJSON, @RequestParam(value = "name") String name,
			@RequestParam(value = "age") Integer age) throws JsonParseException, JsonMappingException, IOException {

		List<LeaderboardPlaceDTO> leaderboard = leaderboardService.postLeaderboardScore(textJSON, errorsJSON, name, 0);

		ModelAndView view = new ModelAndView("leaderboard");
		view.addObject("placement", leaderboard);
		view.addObject("leaderboard", leaderboardService.getTopLeaderboard(10));
		return view;
	}

}

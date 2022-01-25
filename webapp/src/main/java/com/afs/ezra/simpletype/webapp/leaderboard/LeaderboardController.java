package com.afs.ezra.simpletype.webapp.leaderboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LeaderboardController {

	private final LeaderboardManager manager;

	@PostMapping("/post-leaderboard")
	public ModelAndView postLeaderboardScore(@RequestParam("text[]") String textJSON,
			@RequestParam("errors[]") String errorsJSON, @RequestParam(value = "name") String name) {

		LeaderboardPlaceDTO[] leaderboard = manager.postLeaderboardScore(textJSON, errorsJSON, name);

		ModelAndView view = new ModelAndView("leaderboard");
		view.addObject("placement", leaderboard);
		view.addObject("leaderboard", manager.getTopLeaderboard(10));
		return view;
	}

}

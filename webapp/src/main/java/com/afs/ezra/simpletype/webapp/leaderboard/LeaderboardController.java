package com.afs.ezra.simpletype.webapp.leaderboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LeaderboardController {

	private final LeaderboardManager manager;

	@PostMapping("/leaderboard")
	public ModelAndView postLeaderboardScore(@ModelAttribute TypingTest testData) {

		LeaderboardPlace placement = manager.postLeaderboardScore(testData);

		ModelAndView view = new ModelAndView("leaderboard");
		view.addObject("placement", placement);
		view.addObject("leaderboard", manager.getTopLeaderboard(10));
		return view;
	}

}

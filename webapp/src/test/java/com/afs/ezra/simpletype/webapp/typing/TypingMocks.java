package com.afs.ezra.simpletype.webapp.typing;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.afs.ezra.simpletype.webapp.leaderboard.LeaderboardManager;

@Profile("test")
@Configuration
public class TypingMocks {
	
	@Bean
	@Primary
	public LeaderboardManager leaderboardManager() {
		return Mockito.mock(LeaderboardManager.class);
	}

	@Bean
	@Primary
	public TypingService typingService() {
		return Mockito.mock(TypingService.class);
	}
}

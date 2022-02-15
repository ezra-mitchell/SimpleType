package com.afs.ezra.simpletype.provider.leaderboard.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.afs.ezra.simpletype.provider.leaderboard.repo.LeaderboardRepository;

@Profile("test")
@Configuration
public class LeaderboardMocks {

	@Bean
	@Primary
	public LeaderboardRepository leaderboardRepository() {
		return Mockito.mock(LeaderboardRepository.class);
	}
}
